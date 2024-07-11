package commercesyncoffice.org.domain.category.exception;

import commercesyncoffice.org.domain.category.message.ExceptionCode;
import commercesyncoffice.org.global.exception.ExceptionCodeProvider;
import org.springframework.http.HttpStatus;

public class CategoryException extends RuntimeException implements ExceptionCodeProvider {

    private final ExceptionCode exceptionCode;

    public CategoryException(ExceptionCode exceptionCode) {
        super("[Category Exception] : " + exceptionCode.getMessage());
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
