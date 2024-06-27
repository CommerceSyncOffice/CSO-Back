package commercesyncoffice.org.domain.member.controller;

import commercesyncoffice.org.domain.member.dto.MemberLoginDto;
import commercesyncoffice.org.domain.member.dto.MemberPasswordChangeDto;
import commercesyncoffice.org.domain.member.dto.MemberSignUpDto;
import commercesyncoffice.org.domain.member.service.MemberService;
import commercesyncoffice.org.global.jwt.JwtUtil;
import commercesyncoffice.org.global.security.UserDetailsImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final JwtUtil jwtUtil;

    @PostMapping("/brand/{brandId}/member")
    public ResponseEntity<?> signUp(
            @PathVariable Long brandId,
            @RequestBody MemberSignUpDto memberSignUpDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        return ResponseEntity.status(201).body(memberService.signUp(brandId, memberSignUpDto, userDetails));
    }

    @PostMapping("/brand/{brandId}/login")
    public ResponseEntity<?> login(
            @PathVariable Long brandId,
            @RequestBody MemberLoginDto memberLoginDto,
            HttpServletResponse response
    ) {

        String token = memberService.login(brandId, memberLoginDto);
        jwtUtil.addJWTToCookie(token, response);

        return ResponseEntity.ok().body("로그인~~~~~성공!\nToken\n" + token);
    }

    @PatchMapping("/brand/member/password")
    public ResponseEntity<?> changePassword(
            @RequestBody MemberPasswordChangeDto memberPasswordChangeDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        memberService.changePassword(memberPasswordChangeDto, userDetails);

        return ResponseEntity.status(200).body("비밀번호 변경 완료");
    }
}
