package commercesyncoffice.org.domain.stockrequest.controller;

import commercesyncoffice.org.domain.stockrequest.dto.StockRequestCreateDto;
import commercesyncoffice.org.domain.stockrequest.service.StockRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class StockRequestController {

    private final StockRequestService stockRequestService;

    @PostMapping("/brand/store/{storeId}/stock_request")
    public String createStockRequest(
            @RequestBody @Valid StockRequestCreateDto stockRequestCreateDto,
            @PathVariable Long storeId
    ) {

        createStockRequest(stockRequestCreateDto, storeId);

        return "redirect:/brand/store/" + storeId + "/stock_request";
    }
}
