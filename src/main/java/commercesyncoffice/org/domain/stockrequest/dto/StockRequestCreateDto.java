package commercesyncoffice.org.domain.stockrequest.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record StockRequestCreateDto(

        @NotNull(message = "아이템 번호를 입력해주세요.")
        Long itemId,

        @NotNull(message = "발주 수량을 입력해주세요.")
        @Positive(message = "발주 수량은 양수만 입력할 수 있습니다.")
        Integer stockRequestCnt
) {

}
