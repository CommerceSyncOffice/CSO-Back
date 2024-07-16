package commercesyncoffice.org.domain.admin.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

    NOT_FOUND_ADMIN(HttpStatus.NOT_FOUND, "해당 어드민 계정은 존재하지 않습니다."),
    NOT_MATCH_PASSWORD_WITH_USERNAME_IN_ADMIN(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    SAME_USERNAME_IN_ADMIN(HttpStatus.CONFLICT, "이미 존재하는 아이디입니다."),
    SAME_EMAIL_IN_ADMIN(HttpStatus.CONFLICT, "이미 존재하는 이메일입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
