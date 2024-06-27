package commercesyncoffice.org.domain.brand.service;

import commercesyncoffice.org.domain.brand.Brand;
import commercesyncoffice.org.domain.brand.dto.BrandCreateDto;
import commercesyncoffice.org.domain.brand.dto.GetBrandListDto;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;

public interface BrandService {

    void createBrand(BrandCreateDto brandCreateDto, UserDetails userDetails);

    List<GetBrandListDto> getBrandList(UserDetails userDetails);

    Brand getBrandById(Long brandId);

    void checkBrand(Long brandId);

    boolean existsByIdAndAdminUsername(Long brandId, String username);

//    void validateBrand(Member member, Long brandId);
//
//    void validateBrand(Admin admin, Long brandId);

    void validateBrand(UserDetails userDetails, Long brandId);
}
