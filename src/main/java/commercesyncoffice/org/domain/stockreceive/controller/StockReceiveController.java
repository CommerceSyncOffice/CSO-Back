package commercesyncoffice.org.domain.stockreceive.controller;

import static commercesyncoffice.org.domain.stockreceive.message.SuccessMessage.*;
import static commercesyncoffice.org.global.response.SuccessResponse.*;

import commercesyncoffice.org.domain.stockreceive.dto.request.StockReceiveCreateDto;
import commercesyncoffice.org.domain.stockreceive.message.SuccessMessage;
import commercesyncoffice.org.domain.stockreceive.service.StockReceiveService;
import commercesyncoffice.org.global.response.CommonResponse;
import commercesyncoffice.org.global.response.SuccessResponse;
import commercesyncoffice.org.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class StockReceiveController {

    private final StockReceiveService stockReceiveService;

    @PostMapping("/brand/stock_receive")
    public ResponseEntity<? extends CommonResponse> createStockReceive(
            @RequestBody @Valid StockReceiveCreateDto stockReceiveCreateDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        stockReceiveService.createStockReceive(userDetails, stockReceiveCreateDto);

        return ResponseEntity.status(SUCCESS_CREATE_STOCK_RECEIVE.getHttpStatus())
                             .body(success(SUCCESS_CREATE_STOCK_RECEIVE.getMessage()));
    }
}
