package commercesyncoffice.org.domain.brand.service;

import commercesyncoffice.org.domain.brand.model.Brand;
import commercesyncoffice.org.domain.brand.dto.request.BrandCreateDto;
import commercesyncoffice.org.domain.brand.dto.response.GetBrandListDto;
import commercesyncoffice.org.domain.brand.model.BrandId;
import commercesyncoffice.org.domain.membergroup.model.MemberGroupId;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;

public interface BrandService {

    void createBrand(BrandCreateDto brandCreateDto, UserDetails userDetails);

    List<GetBrandListDto> getBrandList(UserDetails userDetails);

    Brand getBrandById(Long brandId);

    void checkBrand(Long brandId);

    void validateBrand(UserDetails userDetails, BrandId brandId);
    void validateBrand(UserDetails userDetails, MemberGroupId brandId);
}
