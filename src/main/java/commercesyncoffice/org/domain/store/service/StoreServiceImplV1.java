package commercesyncoffice.org.domain.store.service;

import commercesyncoffice.org.domain.brand.Brand;
import commercesyncoffice.org.domain.brand.service.BrandService;
import commercesyncoffice.org.domain.store.Store;
import commercesyncoffice.org.domain.store.dto.StoreCreateDto;
import commercesyncoffice.org.domain.store.dto.StoreListDto;
import commercesyncoffice.org.domain.store.repository.StoreRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Primary
public class StoreServiceImplV1 implements StoreService {

    private final StoreRepository storeRepository;
    private final BrandService brandService;

    @Override
    public Long createStore(Long brandId, StoreCreateDto storeCreateDto) {

        Brand brand = brandService.getBrandById(brandId);

        return storeRepository.save(Store.createStore(brand, storeCreateDto)).getId();
    }

    @Override
    public List<StoreListDto> getStoreList(Long brandId) {

        brandService.checkBrand(brandId);

        return storeRepository.findStoreListByBrand(brandId);
    }
}
