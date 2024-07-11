package commercesyncoffice.org.global.exception;

import org.springframework.http.HttpStatus;

public interface ExceptionCodeProvider {
    HttpStatus getHttpStatus();
    String getMessage();
}
