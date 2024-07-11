package commercesyncoffice.org.domain.admin.service;

import commercesyncoffice.org.domain.admin.model.Admin;
import commercesyncoffice.org.domain.admin.dto.request.AdminLoginDto;
import commercesyncoffice.org.domain.admin.dto.request.AdminSignUpDto;

public interface AdminService {

    Admin getAdminById(Long adminId);

    Admin getAdminByUsername(String username);

    void signup(AdminSignUpDto adminSignUpDto);

    String login(AdminLoginDto adminLoginDto);

}
