package commercesyncoffice.org.domain.stockreceive.controller;

import commercesyncoffice.org.domain.stockreceive.dto.StockReceiveCreateDto;
import commercesyncoffice.org.domain.stockreceive.service.StockReceiveService;
import commercesyncoffice.org.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class StockReceiveController {

    private final StockReceiveService stockReceiveService;

    @PostMapping("/brand/stock_receive")
    public String createStockReceive(
            @RequestBody @Valid StockReceiveCreateDto stockReceiveCreateDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        stockReceiveService.createStockReceive(userDetails, stockReceiveCreateDto);

        return "redirect:/brand/stock_receive";
    }
}
