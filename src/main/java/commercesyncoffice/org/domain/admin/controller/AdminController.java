package commercesyncoffice.org.domain.admin.controller;

import static commercesyncoffice.org.domain.admin.message.SuccessMessage.SUCCESS_LOGIN;
import static commercesyncoffice.org.domain.admin.message.SuccessMessage.SUCCESS_SIGN_UP;
import static commercesyncoffice.org.global.response.SuccessResponse.*;

import commercesyncoffice.org.domain.admin.dto.request.AdminLoginDto;
import commercesyncoffice.org.domain.admin.dto.request.AdminSignUpDto;
import commercesyncoffice.org.domain.admin.service.AdminService;
import commercesyncoffice.org.global.jwt.JwtUtil;
import commercesyncoffice.org.global.response.CommonResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final JwtUtil jwtUtil;

    @PostMapping("/admin/signup")
    public ResponseEntity<? extends CommonResponse> signup(
            @RequestBody @Valid AdminSignUpDto adminSignUpDto
    ) {

        adminService.signup(adminSignUpDto);

        return ResponseEntity.status(SUCCESS_LOGIN.getHttpStatus()).body(success(SUCCESS_LOGIN.getMessage()));
    }

    @PostMapping("/admin/login")
    public ResponseEntity<? extends CommonResponse> login(
            @RequestBody @Valid AdminLoginDto adminLoginDto,
            HttpServletResponse response
    ) {

        String JWTToken = adminService.login(adminLoginDto);
        jwtUtil.addJWTToCookie(JWTToken, response);

        return ResponseEntity.status(SUCCESS_SIGN_UP.getHttpStatus()).body(success(SUCCESS_SIGN_UP.getMessage()));
    }
}
