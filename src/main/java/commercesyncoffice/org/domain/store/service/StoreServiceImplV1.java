package commercesyncoffice.org.domain.store.service;

import commercesyncoffice.org.domain.brand.model.Brand;
import commercesyncoffice.org.domain.brand.model.BrandId;
import commercesyncoffice.org.domain.brand.service.BrandService;
import commercesyncoffice.org.domain.store.exception.StoreException;
import commercesyncoffice.org.domain.store.message.ExceptionCode;
import commercesyncoffice.org.domain.store.model.Store;
import commercesyncoffice.org.domain.store.dto.request.StoreCreateDto;
import commercesyncoffice.org.domain.store.dto.response.StoreListDto;
import commercesyncoffice.org.domain.store.repository.StoreRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Primary
public class StoreServiceImplV1 implements StoreService {

    private final StoreRepository storeRepository;
    private final BrandService brandService;

    @Override
    @Transactional
    public Long createStore(UserDetails userDetails, Long brandId, StoreCreateDto storeCreateDto) {

        brandService.validateBrand(userDetails, BrandId.from(brandId));
        Brand brand = brandService.getBrandById(brandId);

        return storeRepository.save(Store.of(brand, storeCreateDto)).getId();
    }

    @Override
    @Transactional(readOnly = true)
    public List<StoreListDto> getStoreList(UserDetails userDetails, Long brandId) {

        brandService.validateBrand(userDetails, BrandId.from(brandId));
        brandService.checkBrand(brandId);

        return storeRepository.findStoreListByBrand(brandId);
    }

    @Override
    public Store getStoreById(Long storeId) {

        return storeRepository.findById(storeId).orElseThrow(
                () -> new StoreException(ExceptionCode.NOT_FOUND_STORE)
        );
    }
}
