package commercesyncoffice.org.domain.membergrouprole.exception;


import commercesyncoffice.org.domain.membergroupmember.message.ExceptionCode;
import commercesyncoffice.org.global.exception.ExceptionCodeProvider;
import org.springframework.http.HttpStatus;

public class MemberGroupRoleException extends RuntimeException implements ExceptionCodeProvider {

    private final ExceptionCode exceptionCode;

    public MemberGroupRoleException(ExceptionCode exceptionCode) {
        super("[MemberGroupRole Exception] : " + exceptionCode.getMessage());
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
