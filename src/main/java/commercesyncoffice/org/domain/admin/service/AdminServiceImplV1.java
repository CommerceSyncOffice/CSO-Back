package commercesyncoffice.org.domain.admin.service;

import commercesyncoffice.org.domain.account.AccountDto;
import commercesyncoffice.org.domain.admin.Admin;
import commercesyncoffice.org.domain.admin.dto.AdminLoginDto;
import commercesyncoffice.org.domain.admin.dto.AdminSignUpDto;
import commercesyncoffice.org.domain.admin.repository.AdminRepository;
import commercesyncoffice.org.global.exception.CustomException;
import commercesyncoffice.org.global.exception.ExceptionCode;
import commercesyncoffice.org.global.jwt.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Primary
@Service
@RequiredArgsConstructor
public class AdminServiceImplV1 implements AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public void signup(AdminSignUpDto adminSignUpDto) {

        if (adminRepository.existsByUsername(adminSignUpDto.username())) {
            throw new CustomException(ExceptionCode.SAME_USERNAME_IN_ADMIN);
        }

        if (adminRepository.existsByEmail(adminSignUpDto.email())) {
            throw new CustomException(ExceptionCode.SAME_EMAIL_IN_ADMIN);
        }

        String encodedPassword = passwordEncoder.encode(adminSignUpDto.password());

        Admin admin = Admin.signup(adminSignUpDto, encodedPassword);

        adminRepository.save(admin);
    }

    @Override
    public String login(AdminLoginDto adminLoginDto) {

        Admin admin = adminRepository.findByUsername(adminLoginDto.username()).orElseThrow(
                () -> new CustomException(ExceptionCode.NOT_FOUND_USERNAME_IN_ADMIN)
        );

        if (!passwordEncoder.matches(adminLoginDto.password(), admin.getPassword())) {
            throw new CustomException(ExceptionCode.NOT_MATCH_PASSWORD_WITH_USERNAME_IN_ADMIN);
        }

        AccountDto accountDto = new AccountDto(admin.getUsername(), JwtUtil.ADMIN);

        return jwtUtil.createToken(accountDto);
    }

    @Override
    public Admin getAdminById(Long adminId) {
        return adminRepository.findById(adminId).orElseThrow(
                () -> new CustomException(ExceptionCode.NOT_FOUND_ADMIN)
        );
    }

    @Override
    public Admin getAdminByUsername(String username) {
        return adminRepository.findByUsername(username).orElseThrow(
                () -> new CustomException(ExceptionCode.NOT_FOUND_ADMIN)
        );
    }
}
