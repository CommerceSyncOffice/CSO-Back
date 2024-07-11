package commercesyncoffice.org.domain.storeitem.service;

import commercesyncoffice.org.domain.storeitem.dto.request.StoreItemCreateDto;
import commercesyncoffice.org.domain.storeitem.dto.request.StoreItemSaleDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface StoreItemService {

    void createStoreItem(UserDetails userDetails, Long storeId, StoreItemCreateDto storeItemCreateDto);

    void saleStoreItem(UserDetails userDetails, Long storeItemId, StoreItemSaleDto storeItemSaleDto);
}
