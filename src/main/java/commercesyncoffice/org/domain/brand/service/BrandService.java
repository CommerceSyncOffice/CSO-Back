package commercesyncoffice.org.domain.brand.service;

import commercesyncoffice.org.domain.brand.dto.BrandCreateDto;
import commercesyncoffice.org.domain.brand.dto.GetBrandListDto;
import java.util.List;

public interface BrandService {

    void createBrand(BrandCreateDto brandCreateDto, Long adminId);

    List<GetBrandListDto> getBrandList(Long adminId);
}
