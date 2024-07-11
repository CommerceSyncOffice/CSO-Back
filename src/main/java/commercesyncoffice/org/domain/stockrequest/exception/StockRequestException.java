package commercesyncoffice.org.domain.stockrequest.exception;

import commercesyncoffice.org.domain.stockrequest.message.ExceptionCode;
import commercesyncoffice.org.global.exception.ExceptionCodeProvider;
import org.springframework.http.HttpStatus;

public class StockRequestException extends RuntimeException implements ExceptionCodeProvider {

    private final ExceptionCode exceptionCode;

    public StockRequestException(ExceptionCode exceptionCode) {
        super("[StockRequest Exception] : " + exceptionCode.getMessage());
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
