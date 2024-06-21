package commercesyncoffice.org.domain.admin.service;

import commercesyncoffice.org.domain.admin.Admin;
import commercesyncoffice.org.domain.admin.dto.AdminLoginDto;
import commercesyncoffice.org.domain.admin.dto.AdminSignUpDto;

public interface AdminService {

    Admin getAdminById(Long adminId);

    void signup(AdminSignUpDto adminSignUpDto);

    String login(AdminLoginDto adminLoginDto);
}
