package commercesyncoffice.org.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ExceptionCode {

    // NOT FOUND 모음
    NOT_FOUND_STORE(HttpStatus.NOT_FOUND, "해당 스토어는 존재하지 않습니다."),
    NOT_FOUND_STORE_ITEM(HttpStatus.NOT_FOUND, "해당 스토어 아이템은 존재하지 않습니다."),

    TOKEN_INVALID(HttpStatus.FORBIDDEN, "유효하지 않은 토큰입니다."),
    TOKEN_EXPIRED(HttpStatus.FORBIDDEN, "만료된 토큰입니다."),
    TOKEN_UNSUPPORTED(HttpStatus.FORBIDDEN, "지원되지 않는 토큰입니다."),
    TOKEN_EMPTY(HttpStatus.FORBIDDEN, "잘못된 토큰입니다."),

    URL_ENCODING_EXCEPTION(HttpStatus.BAD_REQUEST, "토큰 인코딩 실패");

    private final HttpStatus httpStatus;
    private final String message;
}
