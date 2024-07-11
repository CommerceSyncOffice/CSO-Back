package commercesyncoffice.org.domain.store.service;

import commercesyncoffice.org.domain.store.model.Store;
import commercesyncoffice.org.domain.store.dto.StoreCreateDto;
import commercesyncoffice.org.domain.store.dto.StoreListDto;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;

public interface StoreService {

    Long createStore(UserDetails userDetails, Long brandId, StoreCreateDto storeCreateDto);

    List<StoreListDto> getStoreList(UserDetails userDetails, Long brandId);

    Store getStoreById(Long storeId);
}
