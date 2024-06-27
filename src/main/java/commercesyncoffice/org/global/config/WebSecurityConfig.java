package commercesyncoffice.org.global.config;

import commercesyncoffice.org.domain.memberrole.MemberRoleEnum;
import commercesyncoffice.org.global.jwt.JwtAuthorizationFilter;
import commercesyncoffice.org.global.jwt.JwtUtil;
import commercesyncoffice.org.global.security.AdminUserDetailService;
import commercesyncoffice.org.global.security.MemberUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtUtil jwtUtil;
    private final MemberUserDetailService memberUserDetailService;
    private final AdminUserDetailService adminUserDetailService;

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() {

        return new JwtAuthorizationFilter(adminUserDetailService, memberUserDetailService, jwtUtil);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
            throws Exception {

        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests((authorizeHttpRequests) ->
                authorizeHttpRequests.requestMatchers(HttpMethod.POST, "/brand/*/member").hasAuthority(MemberRoleEnum.ROLE_CREATE_MEMBER.name())
                                     .requestMatchers(HttpMethod.POST, "/brand").hasAuthority(MemberRoleEnum.ROLE_CREATE_BRAND.name())
                                     .anyRequest().permitAll()

        );

        http.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
