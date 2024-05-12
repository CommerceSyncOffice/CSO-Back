package commercesyncoffice.org.domain.storeitem.service;

import commercesyncoffice.org.domain.storeitem.dto.StoreItemCreateDto;
import commercesyncoffice.org.domain.storeitem.dto.StoreItemSaleDto;

public interface StoreItemService {

    void createStoreItem(Long storeId, StoreItemCreateDto storeItemCreateDto);

    void saleStoreItem(Long storeItemId, StoreItemSaleDto storeItemSaleDto);
}
