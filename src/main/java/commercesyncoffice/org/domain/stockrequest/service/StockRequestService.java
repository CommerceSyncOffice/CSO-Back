package commercesyncoffice.org.domain.stockrequest.service;

import commercesyncoffice.org.domain.stockrequest.dto.StockRequestCreateDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface StockRequestService {

    void createStockRequest(UserDetails userDetails, StockRequestCreateDto stockRequestCreateDto, Long storeId);
}
