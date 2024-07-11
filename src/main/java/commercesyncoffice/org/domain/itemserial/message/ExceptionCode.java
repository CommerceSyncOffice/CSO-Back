package commercesyncoffice.org.domain.itemserial.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

    SAME_SERIAL_IN_ITEM(HttpStatus.CONFLICT, "아이템 내에 해당 시리얼이 이미 존재합니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
