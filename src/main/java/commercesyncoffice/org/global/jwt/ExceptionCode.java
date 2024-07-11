package commercesyncoffice.org.global.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ExceptionCode {

    TOKEN_INVALID(HttpStatus.FORBIDDEN, "유효하지 않은 토큰입니다."),
    TOKEN_EXPIRED(HttpStatus.FORBIDDEN, "만료된 토큰입니다."),
    TOKEN_UNSUPPORTED(HttpStatus.FORBIDDEN, "지원되지 않는 토큰입니다."),
    TOKEN_EMPTY(HttpStatus.FORBIDDEN, "잘못된 토큰입니다."),
    URL_ENCODING_EXCEPTION(HttpStatus.BAD_REQUEST, "토큰 인코딩 실패"),
    NOT_FOUND_TOKEN(HttpStatus.NOT_FOUND, "해당 토큰은 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
