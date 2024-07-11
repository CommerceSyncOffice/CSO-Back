package commercesyncoffice.org.domain.item.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

    ALREADY_REGISTERED_ITEM(HttpStatus.BAD_REQUEST, "이미 등록한 아이템입니다."),
    SAME_BARCODE_IN_BRAND(HttpStatus.CONFLICT, "브랜드 내에 해당 바코드가 이미 존재합니다."),
    CAN_NOT_SALE_MORE(HttpStatus.CONFLICT, "재고가 부족합니다. 더 이상 팔 수 없습니다."),
    DELETE_SERIAL_THIS_ITEM(HttpStatus.BAD_REQUEST, "아이템에 등록된 시리얼 번호를 지우고 다시 시도해주세요."),
    NOT_FOUND_ITEM(HttpStatus.NOT_FOUND, "해당 아이템은 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
