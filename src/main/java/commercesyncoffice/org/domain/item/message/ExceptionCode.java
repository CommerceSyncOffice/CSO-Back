package commercesyncoffice.org.domain.item.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

    SAME_BARCODE_IN_BRAND(HttpStatus.CONFLICT, "브랜드 내에 해당 바코드가 이미 존재합니다."),
    DELETE_SERIAL_THIS_ITEM(HttpStatus.BAD_REQUEST, "아이템에 등록된 시리얼 번호를 지우고 다시 시도해주세요."),
    NOT_FOUND_ITEM(HttpStatus.NOT_FOUND, "해당 아이템은 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
