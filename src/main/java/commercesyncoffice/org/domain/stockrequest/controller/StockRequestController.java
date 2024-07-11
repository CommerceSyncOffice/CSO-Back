package commercesyncoffice.org.domain.stockrequest.controller;

import static commercesyncoffice.org.domain.stockrequest.message.SuccessMessage.*;
import static commercesyncoffice.org.global.response.SuccessResponse.*;

import commercesyncoffice.org.domain.stockrequest.dto.StockRequestCreateDto;
import commercesyncoffice.org.domain.stockrequest.message.SuccessMessage;
import commercesyncoffice.org.domain.stockrequest.service.StockRequestService;
import commercesyncoffice.org.global.response.CommonResponse;
import commercesyncoffice.org.global.response.SuccessResponse;
import commercesyncoffice.org.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StockRequestController {

    private final StockRequestService stockRequestService;

    @PostMapping("/brand/store/{storeId}/stock_request")
    public ResponseEntity<? extends CommonResponse> createStockRequest(
            @RequestBody @Valid StockRequestCreateDto stockRequestCreateDto,
            @PathVariable Long storeId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        stockRequestService.createStockRequest(userDetails, stockRequestCreateDto, storeId);

        return ResponseEntity.status(SUCCESS_CREATE_STOCK_REQUEST.getHttpStatus())
                             .body(success(SUCCESS_CREATE_STOCK_REQUEST.getMessage(), storeId));
    }
}
