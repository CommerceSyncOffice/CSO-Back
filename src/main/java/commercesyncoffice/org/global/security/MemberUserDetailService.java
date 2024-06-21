package commercesyncoffice.org.global.security;

import commercesyncoffice.org.domain.member.Member;
import commercesyncoffice.org.domain.member.repository.MemberRepository;
import commercesyncoffice.org.global.exception.CustomException;
import commercesyncoffice.org.global.exception.ExceptionCode;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // username = "brandId_username"
        String[] parts = username.split("_");
        Long brandId = Long.valueOf(parts[0]);
        username = parts[1];

        Member member = memberRepository.findByUsernameAndBrandId(username, brandId).orElseThrow(
                () -> new CustomException(ExceptionCode.NOT_FOUND_MEMBER)
        );

        List<String> roles = memberRepository.findMemberRolesByUsernameAndBrandId(username, brandId);

        return User.builder()
                .username(member.getUsername())
                .password(member.getPassword())
                .authorities(roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()))
                .build();
    }
}
