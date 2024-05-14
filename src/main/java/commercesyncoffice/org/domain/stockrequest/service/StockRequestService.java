package commercesyncoffice.org.domain.stockrequest.service;

import commercesyncoffice.org.domain.stockrequest.dto.StockRequestCreateDto;

public interface StockRequestService {

    void createStockRequest(StockRequestCreateDto stockRequestCreateDto, Long storeId);
}
