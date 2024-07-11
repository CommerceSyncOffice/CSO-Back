package commercesyncoffice.org.domain.item.exception;

import commercesyncoffice.org.domain.item.message.ExceptionCode;
import commercesyncoffice.org.global.exception.ExceptionCodeProvider;
import org.springframework.http.HttpStatus;

public class ItemException extends RuntimeException implements ExceptionCodeProvider {

    private final ExceptionCode exceptionCode;

    public ItemException(ExceptionCode exceptionCode) {
        super("[Item Exception] : " + exceptionCode.getMessage());
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
