package commercesyncoffice.org.domain.stockrequest.controller;

import commercesyncoffice.org.domain.stockrequest.dto.StockRequestCreateDto;
import commercesyncoffice.org.domain.stockrequest.service.StockRequestService;
import commercesyncoffice.org.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
            @PathVariable Long storeId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        stockRequestService.createStockRequest(userDetails, stockRequestCreateDto, storeId);

        return "redirect:/brand/store/" + storeId + "/stock_request";
    }
}
