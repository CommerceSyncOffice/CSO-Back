package commercesyncoffice.org.domain.member.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

    SAME_USERNAME_IN_MEMBER(HttpStatus.CONFLICT, "이미 존재하는 아이디입니다."),
    NOT_MATCH_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, "해당 멤버는 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
