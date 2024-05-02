package commercesyncoffice.org.domain.storeitem.service;

import commercesyncoffice.org.domain.storeitem.dto.StoreItemCreateDto;

public interface StoreItemService {

    void createStoreItem(Long storeId, StoreItemCreateDto storeItemCreateDto);
}
