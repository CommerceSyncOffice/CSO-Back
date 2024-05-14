package commercesyncoffice.org.domain.stockreceive.service;

import commercesyncoffice.org.domain.item.Item;
import commercesyncoffice.org.domain.item.service.ItemService;
import commercesyncoffice.org.domain.stockreceive.StockReceive;
import commercesyncoffice.org.domain.stockreceive.dto.StockReceiveCreateDto;
import commercesyncoffice.org.domain.stockreceive.repository.StockReceiveRepository;
import commercesyncoffice.org.domain.store.Store;
import commercesyncoffice.org.domain.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StockReceiveServiceImplV1 implements StockReceiveService{

    private final StockReceiveRepository stockReceiveRepository;
    private final StoreService storeService;
    private final ItemService itemService;

    @Override
    public void createStockReceive(StockReceiveCreateDto stockReceiveCreateDto) {

        Store store = storeService.getStoreById(stockReceiveCreateDto.storeId());
        Item item = itemService.getItemById(stockReceiveCreateDto.itemId());

        stockReceiveRepository.save(StockReceive.createStockReceive(stockReceiveCreateDto, item, store));
    }
}
