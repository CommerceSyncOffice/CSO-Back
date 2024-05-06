package commercesyncoffice.org.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ExceptionCode {

    ALREADY_REGISTERED_ITEM(HttpStatus.BAD_REQUEST, "이미 등록한 아이템입니다."),
    SAME_BARCODE_IN_BRAND(HttpStatus.CONFLICT, "브랜드 내에 해당 바코드가 이미 존재합니다."),
    DELETE_SERIAL_THIS_ITEM(HttpStatus.BAD_REQUEST, "아이템에 등록된 시리얼 번호를 지우고 다시 시도해주세요."),

    NOT_FOUND_BRAND(HttpStatus.BAD_REQUEST, "해당 브랜드는 존재하지 않습니다."),
    NOT_FOUND_CATEGORY(HttpStatus.NOT_FOUND, "해당 카테고리는 존재하지 않습니다"),
    NOT_FOUND_ITEM(HttpStatus.NOT_FOUND, "해당 아이템은 존재하지 않습니다."),
    NOT_FOUND_STORE(HttpStatus.NOT_FOUND, "해당 스토어는 존재하지 않습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
