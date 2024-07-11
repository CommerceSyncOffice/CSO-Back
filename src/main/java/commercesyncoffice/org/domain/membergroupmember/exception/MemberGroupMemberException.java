package commercesyncoffice.org.domain.membergroupmember.exception;


import commercesyncoffice.org.domain.membergroupmember.message.ExceptionCode;
import commercesyncoffice.org.global.exception.ExceptionCodeProvider;
import org.springframework.http.HttpStatus;

public class MemberGroupMemberException extends RuntimeException implements ExceptionCodeProvider {

    private final ExceptionCode exceptionCode;

    public MemberGroupMemberException(ExceptionCode exceptionCode) {
        super("[MemberGroupMember Exception] : " + exceptionCode.getMessage());
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
