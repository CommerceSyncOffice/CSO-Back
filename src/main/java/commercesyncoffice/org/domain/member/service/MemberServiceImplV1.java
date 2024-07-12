package commercesyncoffice.org.domain.member.service;

import commercesyncoffice.org.domain.account.AccountDto;
import commercesyncoffice.org.domain.brand.model.Brand;
import commercesyncoffice.org.domain.brand.service.BrandService;
import commercesyncoffice.org.domain.member.exception.MemberException;
import commercesyncoffice.org.domain.member.message.ExceptionCode;
import commercesyncoffice.org.domain.member.model.Member;
import commercesyncoffice.org.domain.member.dto.request.MemberLoginDto;
import commercesyncoffice.org.domain.member.dto.request.MemberPasswordChangeDto;
import commercesyncoffice.org.domain.member.dto.request.MemberSignUpDto;
import commercesyncoffice.org.domain.member.dto.response.MemberSignUpResponseDto;
import commercesyncoffice.org.domain.member.event.MemberSignUpEvent;
import commercesyncoffice.org.domain.member.repository.MemberRepository;
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

        brandService.validateBrand(userDetails, brandId);

        if (memberRepository.existsByUsername(brandId + "_" + memberSignUpDto.username())) {
            throw new MemberException(ExceptionCode.SAME_USERNAME_IN_MEMBER);
        }

        Member member = Member.of(memberSignUpDto, brand);
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
                () -> new MemberException(ExceptionCode.NOT_FOUND_MEMBER)
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
                () -> new MemberException(ExceptionCode.NOT_FOUND_MEMBER)
        );

        checkPassword(memberPasswordChangeDto.oldPassword(), member);

        member.changePassword(passwordEncoder.encode(memberPasswordChangeDto.newPassword()));
    }

    private void checkPassword(String passwordInput, Member member) {

        if (member.isRandomPassword()) {
            if (!passwordInput.equals(member.getPassword())) {
                throw new MemberException(ExceptionCode.NOT_MATCH_PASSWORD);
            }
        } else {
            if (!passwordEncoder.matches(passwordInput, member.getPassword())) {
                throw new MemberException(ExceptionCode.NOT_MATCH_PASSWORD);
            }
        }
    }
//
//    @Override
//    public Member getMemberByUsername(String username) {
//        return memberRepository.findByUsername(username).orElseThrow(
//                () -> new CustomException(ExceptionCode.NOT_FOUND_MEMBER)
//        );
//    }


    @Override
    public Member getMemberByMemberId(Long memberId) {

        return memberRepository.findById(memberId).orElseThrow(
                () -> new MemberException(ExceptionCode.NOT_FOUND_MEMBER)
        );
    }
}
