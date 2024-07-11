package commercesyncoffice.org.domain.membergroup.exception;


import commercesyncoffice.org.domain.membergroup.message.ExceptionCode;
import commercesyncoffice.org.global.exception.ExceptionCodeProvider;
import org.springframework.http.HttpStatus;

public class MemberGroupException extends RuntimeException implements ExceptionCodeProvider {

    private final ExceptionCode exceptionCode;

    public MemberGroupException(ExceptionCode exceptionCode) {
        super("[MemberGroup Exception] : " + exceptionCode.getMessage());
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
