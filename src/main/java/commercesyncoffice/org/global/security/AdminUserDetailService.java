package commercesyncoffice.org.global.security;

import commercesyncoffice.org.domain.admin.exception.AdminException;
import commercesyncoffice.org.domain.admin.message.ExceptionCode;
import commercesyncoffice.org.domain.admin.model.Admin;
import commercesyncoffice.org.domain.admin.repository.AdminRepository;
import commercesyncoffice.org.domain.memberrole.repository.MemberRoleRepository;
import commercesyncoffice.org.global.jwt.JwtUtil;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminUserDetailService implements UserDetailsService {

    private final AdminRepository adminRepository;
    private final MemberRoleRepository memberRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Admin admin = adminRepository.findByUsername(username).orElseThrow(
                () -> new AdminException(ExceptionCode.NOT_FOUND_ADMIN)
        );

        List<String> roles = memberRoleRepository.findAll()
                                                 .stream().map(
                                                         role -> role.getName().name()
                                                 ).toList();

        return new UserDetailsImpl(
                admin.getUsername(),
                admin.getPassword(),
                JwtUtil.ADMIN,
                roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()),
                true,
                true,
                true,
                true
        );
    }
}
