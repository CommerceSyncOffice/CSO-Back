package commercesyncoffice.org.domain.memberrole.exception;

import commercesyncoffice.org.domain.memberrole.message.ExceptionCode;
import commercesyncoffice.org.global.exception.ExceptionCodeProvider;
import org.springframework.http.HttpStatus;

public class MemberRoleException extends RuntimeException implements ExceptionCodeProvider {

    private final ExceptionCode exceptionCode;

    public MemberRoleException(ExceptionCode exceptionCode) {
        super("[MemberRole Exception] : " + exceptionCode.getMessage());
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
