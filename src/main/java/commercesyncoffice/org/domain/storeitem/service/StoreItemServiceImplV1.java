package commercesyncoffice.org.domain.storeitem.service;

import commercesyncoffice.org.domain.item.Item;
import commercesyncoffice.org.domain.item.service.ItemService;
import commercesyncoffice.org.domain.store.Store;
import commercesyncoffice.org.domain.store.service.StoreService;
import commercesyncoffice.org.domain.storeitem.StoreItem;
import commercesyncoffice.org.domain.storeitem.dto.StoreItemCreateDto;
import commercesyncoffice.org.domain.storeitem.repository.StoreItemRepository;
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
            throw new IllegalArgumentException("이미 등록 된 아이템입니다.");
        }

        storeItemRepository.save(StoreItem.createStoreItem(store, item, storeItemCreateDto));
    }
}
