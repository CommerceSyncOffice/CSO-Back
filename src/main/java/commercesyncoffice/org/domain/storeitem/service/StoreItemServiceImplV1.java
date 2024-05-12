package commercesyncoffice.org.domain.storeitem.service;

import commercesyncoffice.org.domain.item.Item;
import commercesyncoffice.org.domain.item.service.ItemService;
import commercesyncoffice.org.domain.store.Store;
import commercesyncoffice.org.domain.store.service.StoreService;
import commercesyncoffice.org.domain.storeitem.StoreItem;
import commercesyncoffice.org.domain.storeitem.dto.StoreItemCreateDto;
import commercesyncoffice.org.domain.storeitem.dto.StoreItemSaleDto;
import commercesyncoffice.org.domain.storeitem.repository.StoreItemRepository;
import commercesyncoffice.org.global.exception.CustomException;
import commercesyncoffice.org.global.exception.ExceptionCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreItemServiceImplV1 implements StoreItemService {

    private final StoreItemRepository storeItemRepository;
    private final StoreService storeService;
    private final ItemService itemService;

    @Override
    @Transactional
    public void createStoreItem(Long storeId, StoreItemCreateDto storeItemCreateDto) {

        Store store = storeService.getStoreById(storeId);
        Item item = itemService.getItemById(storeItemCreateDto.itemId());

        if (storeItemRepository.existsByItemIdAndStoreId(item.getId(), store.getId())) {
            throw new CustomException(ExceptionCode.ALREADY_REGISTERED_ITEM);
        }

        storeItemRepository.save(StoreItem.createStoreItem(store, item, storeItemCreateDto));
    }

    @Override
    @Transactional
    public void saleStoreItem(Long storeItemId, StoreItemSaleDto storeItemSaleDto) {

        StoreItem storeItem = storeItemRepository.findByIdWithPessimisticLock(storeItemId).orElseThrow(
                () -> new CustomException(ExceptionCode.NOT_FOUND_STORE_ITEM)
        );

        storeItem.saleStock(storeItemSaleDto);
    }
}
