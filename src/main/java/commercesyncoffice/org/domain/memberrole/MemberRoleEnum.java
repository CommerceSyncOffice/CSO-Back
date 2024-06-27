package commercesyncoffice.org.domain.memberrole;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRoleEnum {

    // Member
    ROLE_CREATE_MEMBER("브랜드 내 멤버 생성 권한"),

    // Store
    ROLE_CREATE_STORE("브랜드 내 스토어 생성 권한"),

    // Item
    ROLE_CREATE_ITEM("브랜드 내 아이템 생성 권한"),

    // Category
    ROLE_CREATE_CATEGORY("브랜드 내 카테고리 생성 권한"),

    // StoreItem
    ROLE_CREATE_STORE_ITEM("스토어 아이템 등록 권한"),

    // ItemSerial
    ROLE_CREATE_ITEM_SERIAL("아이템 시리얼 번호 생성 권한"),

    // StockReceive
    ROLE_CREATE_STOCK_RECEIVE("입고 생성 권한"),

    // StockRequest
    ROLE_CREATE_STOCK_REQUEST("발주 요청 생성 권한"),

    // Test
    ROLE_TEST("하하"),
    ROLE_TEST2("하하");

    private final String description;


}
