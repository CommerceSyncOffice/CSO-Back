package commercesyncoffice.org.domain.brand.service;

import commercesyncoffice.org.domain.admin.Admin;
import commercesyncoffice.org.domain.admin.service.AdminService;
import commercesyncoffice.org.domain.brand.Brand;
import commercesyncoffice.org.domain.brand.dto.BrandCreateDto;
import commercesyncoffice.org.domain.brand.dto.GetBrandListDto;
import commercesyncoffice.org.domain.brand.repository.BrandRepository;
import commercesyncoffice.org.global.exception.CustomException;
import commercesyncoffice.org.global.exception.ExceptionCode;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class BrandServiceImplV1 implements BrandService {

    private final BrandRepository brandRepository;
    private final AdminService adminService;

    @Override
    @Transactional
    public void createBrand(BrandCreateDto brandCreateDto, UserDetails userDetails) {

        Admin admin = adminService.getAdminByUsername(userDetails.getUsername());

        brandRepository.save(Brand.createBrand(brandCreateDto, admin));
    }

    @Override
    public List<GetBrandListDto> getBrandList(Long adminId) {

        Admin admin = adminService.getAdminById(adminId);

        return brandRepository.findAllBrandListByAdminId(admin);
    }

    @Override
    public Brand getBrandById(Long brandId) {

        return brandRepository.findById(brandId).orElseThrow(
                () -> new CustomException(ExceptionCode.NOT_FOUND_BRAND)
        );
    }

    @Override
    public void checkBrand(Long brandId) {

        if (!brandRepository.existsById(brandId)) {
            throw new CustomException(ExceptionCode.NOT_FOUND_BRAND);
        }
    }

    @Override
    public boolean existsByIdAndAdminUsername(Long brandId, String username) {
        return brandRepository.existsByIdAndAdminUsername(brandId, username);
    }
}
