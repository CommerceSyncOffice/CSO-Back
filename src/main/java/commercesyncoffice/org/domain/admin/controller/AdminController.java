package commercesyncoffice.org.domain.admin.controller;

import commercesyncoffice.org.domain.admin.dto.AdminLoginDto;
import commercesyncoffice.org.domain.admin.dto.AdminSignUpDto;
import commercesyncoffice.org.domain.admin.service.AdminService;
import commercesyncoffice.org.global.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final JwtUtil jwtUtil;

    @PostMapping("/admin/signup")
    public ResponseEntity<?> signup(
            @RequestBody @Valid AdminSignUpDto adminSignUpDto
    ) {

        adminService.signup(adminSignUpDto);

        return ResponseEntity.ok().body("GOOD");
    }

    @PostMapping("/admin/login")
    public ResponseEntity<?> login(
            @RequestBody @Valid AdminLoginDto adminLoginDto,
            HttpServletResponse response
    ) {
        String JWTToken = adminService.login(adminLoginDto);
        jwtUtil.addJWTToCookie(JWTToken, response);

        return ResponseEntity.ok().body("Login!!");
    }
}
