package commercesyncoffice.org.domain.store.service;

import commercesyncoffice.org.domain.store.dto.StoreCreateDto;
import commercesyncoffice.org.domain.store.dto.StoreListDto;
import java.util.List;

public interface StoreService {

    Long createStore(Long brandId, StoreCreateDto storeCreateDto);

    List<StoreListDto> getStoreList(Long brandId);
}
