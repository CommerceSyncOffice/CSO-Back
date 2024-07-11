package commercesyncoffice.org.domain.storeitem.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

    ALREADY_REGISTERED_ITEM(HttpStatus.BAD_REQUEST, "이미 등록한 아이템입니다."),
    NOT_FOUND_STORE_ITEM(HttpStatus.NOT_FOUND, "해당 스토어 아이템은 존재하지 않습니다."),
    CAN_NOT_SALE_MORE(HttpStatus.CONFLICT, "재고가 부족합니다. 더 이상 팔 수 없습니다."),
    YOUR_NOT_ADMIN_THIS_BRAND(HttpStatus.BAD_REQUEST, "이 브랜드를 수정할 권한이 없습니다."),
    YOUR_NOT_MEMBER_THIS_BRAND(HttpStatus.BAD_REQUEST, "이 브랜드를 수정할 권한이 없습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
