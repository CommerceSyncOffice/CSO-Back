package commercesyncoffice.org.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ExceptionResponse {
    private final HttpStatus status;
    private final String message;

    public ExceptionResponse(ExceptionCode exceptionCode) {
        this.status = exceptionCode.getHttpStatus();
        this.message = exceptionCode.getMessage();
    }
}
