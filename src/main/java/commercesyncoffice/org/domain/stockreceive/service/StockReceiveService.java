package commercesyncoffice.org.domain.stockreceive.service;

import commercesyncoffice.org.domain.stockreceive.dto.StockReceiveCreateDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface StockReceiveService {

    void createStockReceive(UserDetails userDetails, StockReceiveCreateDto stockReceiveCreateDto);
}
