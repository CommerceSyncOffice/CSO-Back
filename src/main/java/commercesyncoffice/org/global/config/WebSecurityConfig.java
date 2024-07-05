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
                                     .requestMatchers(HttpMethod.POST, "/brand/*/category").hasAuthority(MemberRoleEnum.ROLE_CREATE_CATEGORY.name())
                                     .requestMatchers(HttpMethod.POST, "/brand/*/item").hasAuthority(MemberRoleEnum.ROLE_CREATE_ITEM.name())
                                     .requestMatchers(HttpMethod.PATCH, "/brand/item/*/category").hasAuthority(MemberRoleEnum.ROLE_CHANGE_ITEM_DETAIL.name())
                                     .requestMatchers(HttpMethod.PATCH, "/brand/item/*/isSerial").hasAuthority(MemberRoleEnum.ROLE_CHANGE_ITEM_DETAIL.name())
                                     .requestMatchers(HttpMethod.POST, "/brand/item/*/item_serial").hasAuthority(MemberRoleEnum.ROLE_CREATE_ITEM_SERIAL.name())
                                     .requestMatchers(HttpMethod.POST, "/brand/stock_receive").hasAuthority(MemberRoleEnum.ROLE_CREATE_STOCK_RECEIVE.name())
                                     .requestMatchers(HttpMethod.POST, "/brand/store/*/stock_request").hasAuthority(MemberRoleEnum.ROLE_CREATE_STOCK_REQUEST.name())
                                     .requestMatchers(HttpMethod.POST, "/brand/*/store").hasAuthority(MemberRoleEnum.ROLE_CREATE_STORE.name())
                                     .requestMatchers(HttpMethod.POST, "/brand/store/*/storeItem").hasAuthority(MemberRoleEnum.ROLE_CREATE_STORE_ITEM.name())
                                     .requestMatchers(HttpMethod.PATCH, "/brand/store/storeItem/*/stock_sell").hasAuthority(MemberRoleEnum.ROLE_SALE_STORE_ITEM.name())
                                     .requestMatchers(HttpMethod.POST, "/brand/*/member_group/register").hasAuthority(MemberRoleEnum.ROLE_REGISTER_MEMBER_GROUP.name())
                                     .requestMatchers(HttpMethod.POST, "/brand/*/member_group").hasAuthority(MemberRoleEnum.ROLE_CREATE_MEMBER_GROUP.name())
                                     .requestMatchers(HttpMethod.POST, "/brand/member_group/*/role").hasAuthority(MemberRoleEnum.ROLE_EDIT_MEMBER_GROUP_ROLE.name())
                                     .anyRequest().permitAll()

        );

        http.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
