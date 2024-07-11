package commercesyncoffice.org.domain.itemserial.exception;


import commercesyncoffice.org.domain.itemserial.message.ExceptionCode;
import commercesyncoffice.org.global.exception.ExceptionCodeProvider;
import org.springframework.http.HttpStatus;

public class ItemSerialException extends RuntimeException implements ExceptionCodeProvider {

    private final ExceptionCode exceptionCode;

    public ItemSerialException(ExceptionCode exceptionCode) {
        super("[ItemSerial Exception] : " + exceptionCode.getMessage());
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
