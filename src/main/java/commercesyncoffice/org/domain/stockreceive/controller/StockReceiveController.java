package commercesyncoffice.org.domain.stockreceive.controller;

import commercesyncoffice.org.domain.stockreceive.dto.StockReceiveCreateDto;
import commercesyncoffice.org.domain.stockreceive.service.StockReceiveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class StockReceiveController {

    private final StockReceiveService stockReceiveService;

    @PostMapping("/brand/stock_receive")
    public String createStockReceive(@RequestBody @Valid StockReceiveCreateDto stockReceiveCreateDto) {

        stockReceiveService.createStockReceive(stockReceiveCreateDto);

        return "redirect:/brand/stock_receive";
    }
}
