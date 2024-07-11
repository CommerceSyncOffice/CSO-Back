package commercesyncoffice.org.domain.brand.exception;

import commercesyncoffice.org.domain.brand.message.ExceptionCode;
import commercesyncoffice.org.global.exception.ExceptionCodeProvider;
import org.springframework.http.HttpStatus;

public class BrandException extends RuntimeException implements ExceptionCodeProvider {

    private final ExceptionCode exceptionCode;

    public BrandException(ExceptionCode exceptionCode) {
        super("[Brand Exception] : " + exceptionCode.getMessage());
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
