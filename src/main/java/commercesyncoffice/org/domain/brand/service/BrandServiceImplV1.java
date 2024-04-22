package commercesyncoffice.org.domain.brand.service;

import commercesyncoffice.org.domain.brand.Brand;
import commercesyncoffice.org.domain.brand.dto.BrandCreateDto;
import commercesyncoffice.org.domain.brand.dto.GetBrandListDto;
import commercesyncoffice.org.domain.brand.repository.BrandRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class BrandServiceImplV1 implements BrandService {

    private final BrandRepository brandRepository;


    @Override
    @Transactional
    public void createBrand(BrandCreateDto brandCreateDto) {

        brandRepository.save(Brand.createBrand(brandCreateDto));
    }

    @Override
    public List<GetBrandListDto> getBrandList() {

        return brandRepository.findAllBrandList();
    }
}
