package commercesyncoffice.org.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum
ExceptionCode {

    // Admin ?
    NOT_FOUND_USERNAME_IN_ADMIN(HttpStatus.BAD_REQUEST, "존재하지 않는 어드민 아이디입니다."),
    NOT_MATCH_PASSWORD_WITH_USERNAME_IN_ADMIN(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    SAME_USERNAME_IN_ADMIN(HttpStatus.CONFLICT, "이미 존재하는 아이디입니다."),
    SAME_EMAIL_IN_ADMIN(HttpStatus.CONFLICT, "이미 존재하는 이메일입니다."),

    // Member
    SAME_USERNAME_IN_MEMBER(HttpStatus.CONFLICT, "이미 존재하는 아이디입니다."),
    NOT_MATCH_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),

    // Item
    ALREADY_REGISTERED_ITEM(HttpStatus.BAD_REQUEST, "이미 등록한 아이템입니다."),
    SAME_BARCODE_IN_BRAND(HttpStatus.CONFLICT, "브랜드 내에 해당 바코드가 이미 존재합니다."),
    SAME_SERIAL_IN_ITEM(HttpStatus.CONFLICT, "아이템 내에 해당 시리얼이 이미 존재합니다."),
    CAN_NOT_SALE_MORE(HttpStatus.CONFLICT, "재고가 부족합니다. 더 이상 팔 수 없습니다."),
    DELETE_SERIAL_THIS_ITEM(HttpStatus.BAD_REQUEST, "아이템에 등록된 시리얼 번호를 지우고 다시 시도해주세요."),

    // 브랜드 ?
    YOUR_NOT_ADMIN_THIS_BRAND(HttpStatus.BAD_REQUEST, "이 브랜드를 수정할 권한이 없습니다."),
    YOUR_NOT_MEMBER_THIS_BRAND(HttpStatus.BAD_REQUEST, "이 브랜드를 수정할 권한이 없습니다."),

    // NOT FOUND 모음
    NOT_FOUND_BRAND(HttpStatus.BAD_REQUEST, "해당 브랜드는 존재하지 않습니다."),
    NOT_FOUND_CATEGORY(HttpStatus.NOT_FOUND, "해당 카테고리는 존재하지 않습니다"),
    NOT_FOUND_ITEM(HttpStatus.NOT_FOUND, "해당 아이템은 존재하지 않습니다."),
    NOT_FOUND_STORE(HttpStatus.NOT_FOUND, "해당 스토어는 존재하지 않습니다."),
    NOT_FOUND_STORE_ITEM(HttpStatus.NOT_FOUND, "해당 스토어 아이템은 존재하지 않습니다."),
    NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, "해당 멤버는 존재하지 않습니다."),
    NOT_FOUND_ADMIN(HttpStatus.NOT_FOUND, "해당 어드민 계정은 존재하지 않습니다."),
    NOT_FOUND_MEMBER_GROUP(HttpStatus.NOT_FOUND, "해당 그룹은 존재하지 않습니다."),
    NOT_FOUND_MEMBER_ROLE(HttpStatus.NOT_FOUND, "해당 멤버 권한은 존재하지 않습니다."),

    TOKEN_INVALID(HttpStatus.FORBIDDEN, "유효하지 않은 토큰입니다."),
    TOKEN_EXPIRED(HttpStatus.FORBIDDEN, "만료된 토큰입니다."),
    TOKEN_UNSUPPORTED(HttpStatus.FORBIDDEN, "지원되지 않는 토큰입니다."),
    TOKEN_EMPTY(HttpStatus.FORBIDDEN, "잘못된 토큰입니다."),

    URL_ENCODING_EXCEPTION(HttpStatus.BAD_REQUEST, "토큰 인코딩 실패");

    private final HttpStatus httpStatus;
    private final String message;
}
