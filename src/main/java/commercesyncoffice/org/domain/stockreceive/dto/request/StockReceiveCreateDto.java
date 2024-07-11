package commercesyncoffice.org.domain.stockreceive.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record StockReceiveCreateDto(

        @NotNull(message = "아이템 번호를 입력해주세요.")
        Long itemId,

        @NotNull(message = "스토어 번호를 입력해주세요.")
        Long storeId,

        @NotNull(message = "입고 수량을 입력해주세요")
        @Positive(message = "입고 수량은 양수만 입력 가능합니다.")
        Integer receiveStockCnt
) {

}
