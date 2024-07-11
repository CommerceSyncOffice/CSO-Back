package commercesyncoffice.org.domain.stockreceive.exception;

import commercesyncoffice.org.domain.stockreceive.message.ExceptionCode;
import commercesyncoffice.org.global.exception.ExceptionCodeProvider;
import org.springframework.http.HttpStatus;

public class StockReceiveException extends RuntimeException implements ExceptionCodeProvider {

    private final ExceptionCode exceptionCode;

    public StockReceiveException(ExceptionCode exceptionCode) {
        super("[StockReceiveException] : " + exceptionCode.getMessage());
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
