package commercesyncoffice.org.domain.member.service;

import commercesyncoffice.org.domain.account.AccountDto;
import commercesyncoffice.org.domain.brand.Brand;
import commercesyncoffice.org.domain.brand.service.BrandService;
import commercesyncoffice.org.domain.member.Member;
import commercesyncoffice.org.domain.member.dto.MemberLoginDto;
import commercesyncoffice.org.domain.member.dto.MemberPasswordChangeDto;
import commercesyncoffice.org.domain.member.dto.MemberSignUpDto;
import commercesyncoffice.org.domain.member.dto.MemberSignUpResponseDto;
import commercesyncoffice.org.domain.member.event.MemberSignUpEvent;
import commercesyncoffice.org.domain.member.repository.MemberRepository;
import commercesyncoffice.org.global.exception.CustomException;
import commercesyncoffice.org.global.exception.ExceptionCode;
import commercesyncoffice.org.global.jwt.JwtUtil;
import commercesyncoffice.org.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberServiceImplV1 implements MemberService {

    private final MemberRepository memberRepository;
    private final BrandService brandService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Value("${site.url}")
    private String url;

    @Override
    @Transactional
    public MemberSignUpResponseDto signUp(Long brandId, MemberSignUpDto memberSignUpDto, UserDetailsImpl userDetails) {

        Brand brand = brandService.getBrandById(brandId);

        if (!userDetails.getRole().equals(JwtUtil.ADMIN)) {
            throw new CustomException(ExceptionCode.YOUR_NOT_ADMIN_THIS_BRAND);
        }

        if (!brandService.existsByIdAndAdminUsername(brandId, userDetails.getUsername())) {
            throw new CustomException(ExceptionCode.YOUR_NOT_ADMIN_THIS_BRAND);
        }

        if (memberRepository.existsByUsername(brandId + "_" + memberSignUpDto.username())) {
            throw new CustomException(ExceptionCode.SAME_USERNAME_IN_MEMBER);
        }

        Member member = Member.createMember(memberSignUpDto, brand);
        memberRepository.save(member);

        String loginUrl = url + brandId + "/login";
        applicationEventPublisher.publishEvent(new MemberSignUpEvent(memberSignUpDto.email(), memberSignUpDto.username(),
                member.getPassword(), loginUrl));

        return new MemberSignUpResponseDto(member.getUsername(), member.getPassword());
    }

    @Override
    @Transactional(readOnly = true)
    public String login(Long brandId, MemberLoginDto memberLoginDto) {

        String username = brandId + "_" + memberLoginDto.username();

        Member member = memberRepository.findByUsernameAndBrandId(username, brandId).orElseThrow(
                () -> new CustomException(ExceptionCode.NOT_FOUND_MEMBER)
        );

        checkPassword(memberLoginDto.password(), member);

        AccountDto accountDto = new AccountDto(username, JwtUtil.MEMBER);

        return jwtUtil.createToken(accountDto);
    }

    @Override
    @Transactional
    public void changePassword(MemberPasswordChangeDto memberPasswordChangeDto,
            UserDetailsImpl userDetails) {

        Member member = memberRepository.findByUsername(userDetails.getUsername()).orElseThrow(
                () -> new CustomException(ExceptionCode.NOT_FOUND_MEMBER)
        );

        checkPassword(memberPasswordChangeDto.oldPassword(), member);

        member.changePassword(passwordEncoder.encode(memberPasswordChangeDto.newPassword()));
    }

    private void checkPassword(String passwordInput, Member member) {

        if (member.isRandomPassword()) {
            if (!passwordInput.equals(member.getPassword())) {
                throw new CustomException(ExceptionCode.NOT_MATCH_PASSWORD);
            }
        } else {
            if (!passwordEncoder.matches(passwordInput, member.getPassword())) {
                throw new CustomException(ExceptionCode.NOT_MATCH_PASSWORD);
            }
        }
    }
}
