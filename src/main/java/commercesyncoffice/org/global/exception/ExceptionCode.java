package commercesyncoffice.org.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ExceptionCode {

    ALREADY_REGISTERED_ITEM(HttpStatus.BAD_REQUEST, "이미 등록한 아이템입니다."),

    NOT_FOUND_ITEM(HttpStatus.NOT_FOUND, "해당 아이템은 존재하지 않습니다."),
    NOT_FOUND_STORE(HttpStatus.NOT_FOUND, "해당 스토어는 존재하지 않습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
