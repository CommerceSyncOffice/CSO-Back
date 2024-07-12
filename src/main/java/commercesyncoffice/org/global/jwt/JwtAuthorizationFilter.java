package commercesyncoffice.org.global.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import commercesyncoffice.org.global.response.ExceptionResponse;
import commercesyncoffice.org.global.security.AdminUserDetailService;
import commercesyncoffice.org.global.security.MemberUserDetailService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final AdminUserDetailService adminUserDetailService;
    private final MemberUserDetailService memberUserDetailService;
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String token = jwtUtil.getTokenFromRequest(request);

        if (StringUtils.hasText(token)) {

            try {
                token = jwtUtil.substringToken(token);

                if (!jwtUtil.validateToken(token)) {
                    throw new JwtException(ExceptionCode.TOKEN_CANT_VALIDATE);
                }

                Claims claims = jwtUtil.getUserInfoFromToken(token);
                String username = String.valueOf(claims.get(JwtUtil.USERNAME));

                setAuthentication(username, claims);
            } catch (JwtException e) {
                handleJwtException(e, response);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    public void setAuthentication(String username, Claims claims) {

        SecurityContext context = SecurityContextHolder.createEmptyContext();

        if (claims.get(JwtUtil.ROLE).equals(JwtUtil.MEMBER)) {
            setAuthenticationForMember(username, context);
        } else if (claims.get(JwtUtil.ROLE).equals(JwtUtil.ADMIN)) {
            setAuthenticationForAdmin(username, context);
        }
    }

    public void setAuthenticationForAdmin(String username, SecurityContext context) {

        UserDetails userDetails = adminUserDetailService.loadUserByUsername(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());

        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }

    public void setAuthenticationForMember(String username, SecurityContext context) {

        UserDetails userDetails = memberUserDetailService.loadUserByUsername(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());

        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }

    public void handleJwtException(JwtException e, HttpServletResponse response) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        ExceptionResponse exceptionResponse = ExceptionResponse.of(e.getLocalizedMessage());

        response.setStatus(e.getHttpStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");
        response.getWriter().write(mapper.writeValueAsString(exceptionResponse));
        response.getWriter().flush();
    }
}
