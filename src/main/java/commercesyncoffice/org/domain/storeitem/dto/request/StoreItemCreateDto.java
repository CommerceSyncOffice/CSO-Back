package commercesyncoffice.org.domain.storeitem.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record StoreItemCreateDto(

        @NotNull(message = "어떤 아이템을 등록할 것인지 입력해주세요")
        @Positive(message = "아이템 번호를 정확히 입력해주세요")
        Long itemId,

        @NotNull(message = "재고 수량을 입력해주세요")
        @Positive(message = "재고 수량은 양수로 입력해주세요")
        int stock,

        @NotNull(message = "판매 수량을 입력해주세요. 판매 수량이 없을 시 0을 입력해주세요")
        @Positive(message = "판매 수량은 양수로 입력해주세요")
        int saleStock,

        @Positive(message = "재고 권장 수량은 양수로 입력해주세요")
        int recommendStock
) {

}
