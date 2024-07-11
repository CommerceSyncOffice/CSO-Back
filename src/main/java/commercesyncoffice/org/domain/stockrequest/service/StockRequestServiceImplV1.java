package commercesyncoffice.org.domain.stockrequest.service;

import commercesyncoffice.org.domain.brand.service.BrandService;
import commercesyncoffice.org.domain.item.model.Item;
import commercesyncoffice.org.domain.item.service.ItemService;
import commercesyncoffice.org.domain.stockrequest.model.StockRequest;
import commercesyncoffice.org.domain.stockrequest.dto.StockRequestCreateDto;
import commercesyncoffice.org.domain.stockrequest.repository.StockRequestRepository;
import commercesyncoffice.org.domain.store.model.Store;
import commercesyncoffice.org.domain.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StockRequestServiceImplV1 implements StockRequestService {


    private final StockRequestRepository stockRequestRepository;
    private final StoreService storeService;
    private final ItemService itemService;
    private final BrandService brandService;

    @Override
    @Transactional
    public void createStockRequest(UserDetails userDetails, StockRequestCreateDto stockRequestCreateDto, Long storeId) {

        Store store = storeService.getStoreById(storeId);
        Item item = itemService.getItemWithBrandByItemId(stockRequestCreateDto.itemId());
        brandService.validateBrand(userDetails, item.getBrandId());

        stockRequestRepository.save(StockRequest.of(stockRequestCreateDto, store, item));
    }
}
