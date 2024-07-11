package commercesyncoffice.org.domain.storeitem.exception;

import commercesyncoffice.org.domain.storeitem.message.ExceptionCode;
import commercesyncoffice.org.global.exception.ExceptionCodeProvider;
import org.springframework.http.HttpStatus;

public class StoreItemException extends RuntimeException implements ExceptionCodeProvider {

    private final ExceptionCode exceptionCode;

    public StoreItemException(ExceptionCode exceptionCode) {
        super("[StoreItem Exception] : " + exceptionCode.getMessage());
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
