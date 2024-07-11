package commercesyncoffice.org.domain.member.controller;

import static commercesyncoffice.org.domain.member.message.SuccessMessage.*;
import static commercesyncoffice.org.global.response.SuccessResponse.*;

import commercesyncoffice.org.domain.member.dto.request.MemberLoginDto;
import commercesyncoffice.org.domain.member.dto.request.MemberPasswordChangeDto;
import commercesyncoffice.org.domain.member.dto.request.MemberSignUpDto;
import commercesyncoffice.org.domain.member.message.SuccessMessage;
import commercesyncoffice.org.domain.member.service.MemberService;
import commercesyncoffice.org.global.jwt.JwtUtil;
import commercesyncoffice.org.global.response.CommonResponse;
import commercesyncoffice.org.global.response.SuccessResponse;
import commercesyncoffice.org.global.security.UserDetailsImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
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
    public ResponseEntity<? extends CommonResponse> signUp(
            @PathVariable Long brandId,
            @RequestBody MemberSignUpDto memberSignUpDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        return ResponseEntity.status(SUCCESS_SIGN_UP.getHttpStatus())
                             .body(success(SUCCESS_SIGN_UP.getMessage(), memberService.signUp(brandId, memberSignUpDto, userDetails)));
    }

    @PostMapping("/brand/{brandId}/login")
    public ResponseEntity<? extends CommonResponse> login(
            @PathVariable Long brandId,
            @RequestBody MemberLoginDto memberLoginDto,
            HttpServletResponse response
    ) {

        String token = memberService.login(brandId, memberLoginDto);
        jwtUtil.addJWTToCookie(token, response);

        return ResponseEntity.status(SUCCESS_LOGIN.getHttpStatus())
                             .body(success(SuccessMessage.SUCCESS_LOGIN.getMessage()));
    }

    @PatchMapping("/brand/member/password")
    public ResponseEntity<? extends CommonResponse> changePassword(
            @RequestBody MemberPasswordChangeDto memberPasswordChangeDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        memberService.changePassword(memberPasswordChangeDto, userDetails);

        return ResponseEntity.status(SUCCESS_CHANGE_PASSWORD.getHttpStatus())
                             .body(success(SuccessMessage.SUCCESS_CHANGE_PASSWORD.getMessage()));
    }
}
