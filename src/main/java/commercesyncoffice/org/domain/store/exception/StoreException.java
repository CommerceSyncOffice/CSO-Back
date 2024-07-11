package commercesyncoffice.org.domain.store.exception;

import commercesyncoffice.org.domain.store.message.ExceptionCode;
import commercesyncoffice.org.global.exception.ExceptionCodeProvider;
import org.springframework.http.HttpStatus;

public class StoreException extends RuntimeException implements ExceptionCodeProvider {

    private final ExceptionCode exceptionCode;

    public StoreException(ExceptionCode exceptionCode) {
        super("[Store Exception] : " + exceptionCode.getMessage());
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
