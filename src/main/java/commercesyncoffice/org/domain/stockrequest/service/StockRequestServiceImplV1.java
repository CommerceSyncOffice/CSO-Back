package commercesyncoffice.org.domain.stockrequest.service;

import commercesyncoffice.org.domain.item.Item;
import commercesyncoffice.org.domain.item.service.ItemService;
import commercesyncoffice.org.domain.stockrequest.StockRequest;
import commercesyncoffice.org.domain.stockrequest.dto.StockRequestCreateDto;
import commercesyncoffice.org.domain.stockrequest.repository.StockRequestRepository;
import commercesyncoffice.org.domain.store.Store;
import commercesyncoffice.org.domain.store.service.StoreService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockRequestServiceImplV1 implements StockRequestService {


    private final StockRequestRepository stockRequestRepository;
    private final StoreService storeService;
    private final ItemService itemService;

    @Override
    @Transactional
    public void createStockRequest(StockRequestCreateDto stockRequestCreateDto, Long storeId) {


        Store store = storeService.getStoreById(storeId);
        Item item = itemService.getItemById(stockRequestCreateDto.itemId());

        stockRequestRepository.save(StockRequest.createStockRequest(stockRequestCreateDto, store, item));
    }
}
