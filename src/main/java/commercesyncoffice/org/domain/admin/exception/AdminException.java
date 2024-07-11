package commercesyncoffice.org.domain.admin.exception;

import commercesyncoffice.org.domain.admin.message.ExceptionCode;
import commercesyncoffice.org.global.exception.ExceptionCodeProvider;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AdminException extends RuntimeException implements ExceptionCodeProvider {

    private final ExceptionCode exceptionCode;

    public AdminException(ExceptionCode exceptionCode) {
        super("[Admin Exception] : " + exceptionCode.getMessage());
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
