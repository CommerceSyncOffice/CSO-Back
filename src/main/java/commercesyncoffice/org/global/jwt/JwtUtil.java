package commercesyncoffice.org.global.jwt;

import commercesyncoffice.org.domain.account.AccountDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    private Key key;

    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    private static final String BEARER = "Bearer ";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final Long EXPIRATION_TIME = 60 * 60 * 1000L;
    public static final String ROLE = "ROLE";
    public static final String MEMBER = "Member";
    public static final String ADMIN = "ADMIN";
    public static final String USERNAME = "USERNAME";

    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secret);
        key = Keys.hmacShaKeyFor(bytes);
    }

    public String createToken(AccountDto accountDto) {

        Date date = new Date();

        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", signatureAlgorithm.getValue());

        return BEARER +
                Jwts.builder()
                        .setHeader(header)
                        .claim(USERNAME, accountDto.username())
                        .claim(ROLE, accountDto.role())
                        .setIssuedAt(date)
                        .setExpiration(new Date(date.getTime() + EXPIRATION_TIME))
                        .signWith(key, signatureAlgorithm)
                        .compact();
    }

    public void addJWTToCookie(String token, HttpServletResponse res) {
        try {
            token = URLEncoder.encode(token, "utf-8").replaceAll("\\+", "%20");
            Cookie cookie = new Cookie(AUTHORIZATION_HEADER, token);
            cookie.setPath("/");
            res.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            throw new JwtException(ExceptionCode.URL_ENCODING_EXCEPTION);
        }
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException | SignatureException e) {
            throw new JwtException(ExceptionCode.TOKEN_INVALID);
        } catch (ExpiredJwtException e) {
            throw new JwtException(ExceptionCode.TOKEN_EXPIRED);
        } catch (UnsupportedJwtException e) {
            throw new JwtException(ExceptionCode.TOKEN_UNSUPPORTED);
        } catch (IllegalArgumentException e) {
            throw new JwtException(ExceptionCode.TOKEN_EMPTY);
        }
    }

    public Claims getUserInfoFromToken(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String substringToken(String token) {
        if (StringUtils.hasText(token) && token.startsWith(BEARER)) {
            return token.substring(BEARER.length());
        } else {
            throw new JwtException(ExceptionCode.NOT_FOUND_TOKEN);
        }
    }

    public String getTokenFromRequest(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(AUTHORIZATION_HEADER)) {
                    try {
                        return URLDecoder.decode(cookie.getValue(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        return null;
                    }
                }
            }
        }

        return null;
    }

}
