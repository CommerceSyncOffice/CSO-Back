package commercesyncoffice.org.domain.member.service;

import commercesyncoffice.org.domain.account.AccountDto;
import commercesyncoffice.org.domain.admin.service.AdminService;
import commercesyncoffice.org.domain.brand.Brand;
import commercesyncoffice.org.domain.brand.service.BrandService;
import commercesyncoffice.org.domain.member.Member;
import commercesyncoffice.org.domain.member.dto.MemberLoginDto;
import commercesyncoffice.org.domain.member.dto.MemberSignUpDto;
import commercesyncoffice.org.domain.member.dto.MemberSignUpResponseDto;
import commercesyncoffice.org.domain.member.repository.MemberRepository;
import commercesyncoffice.org.global.exception.CustomException;
import commercesyncoffice.org.global.exception.ExceptionCode;
import commercesyncoffice.org.global.jwt.JwtUtil;
import commercesyncoffice.org.global.security.UserDetailsImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberServiceImplV1 implements MemberService {

    private final MemberRepository memberRepository;
    private final BrandService brandService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public MemberSignUpResponseDto signUp(Long brandId, MemberSignUpDto memberSignUpDto, UserDetailsImpl userDetails) {

        Brand brand = brandService.getBrandById(brandId);

        if (!brandService.existsByIdAndAdminUsername(brandId, userDetails.getUsername())) {
            throw new CustomException(ExceptionCode.YOUR_NOT_ADMIN_THIS_BRAND);
        }

        if (memberRepository.existsByUsername(brandId + "_" + memberSignUpDto.username())) {
            throw new CustomException(ExceptionCode.SAME_USERNAME_IN_MEMBER);
        }

        Member member = Member.createMember(memberSignUpDto, brand);
        memberRepository.save(member);

        return new MemberSignUpResponseDto(member.getUsername(), member.getPassword());
    }

    @Override
    public String login(Long brandId, MemberLoginDto memberLoginDto) {

        String username = brandId + "_" + memberLoginDto.username();

        Member member = memberRepository.findByUsernameAndBrandId(username, brandId).orElseThrow(
                () -> new CustomException(ExceptionCode.NOT_FOUND_MEMBER)
        );

        if (!passwordEncoder.matches(memberLoginDto.password(), member.getPassword())) {
            throw new CustomException(ExceptionCode.NOT_MATCH_PASSWORD_WITH_USERNAME_IN_MEMBER);
        }

        AccountDto accountDto = new AccountDto(memberLoginDto.username(), JwtUtil.MEMBER);

        return jwtUtil.createToken(accountDto);
    }
}
