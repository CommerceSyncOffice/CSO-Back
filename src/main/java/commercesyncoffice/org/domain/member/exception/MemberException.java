package commercesyncoffice.org.domain.member.exception;

import commercesyncoffice.org.domain.member.message.ExceptionCode;
import commercesyncoffice.org.global.exception.ExceptionCodeProvider;
import org.springframework.http.HttpStatus;

public class MemberException extends RuntimeException implements ExceptionCodeProvider {

    private final ExceptionCode exceptionCode;

    public MemberException(ExceptionCode exceptionCode) {
        super("[Member Exception] : " + exceptionCode.getMessage());
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
