package commercesyncoffice.org.global.jwt;

import commercesyncoffice.org.global.exception.ExceptionCodeProvider;
import org.springframework.http.HttpStatus;

public class JwtException extends RuntimeException implements ExceptionCodeProvider {

    private final ExceptionCode exceptionCode;

    public JwtException(ExceptionCode exceptionCode) {
        super("[Jwt Exception] : " + exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return exceptionCode.getHttpStatus();
    }

    @Override
    public String getMessage() {
        return exceptionCode.getMessage();
    }
}
