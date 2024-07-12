package commercesyncoffice.org.domain.brand.service;

import commercesyncoffice.org.domain.admin.model.Admin;
import commercesyncoffice.org.domain.admin.service.AdminService;
import commercesyncoffice.org.domain.brand.model.Brand;
import commercesyncoffice.org.domain.brand.dto.request.BrandCreateDto;
import commercesyncoffice.org.domain.brand.dto.response.GetBrandListDto;
import commercesyncoffice.org.domain.brand.exception.BrandException;
import commercesyncoffice.org.domain.brand.message.ExceptionCode;
import commercesyncoffice.org.domain.brand.model.BrandId;
import commercesyncoffice.org.domain.brand.repository.BrandRepository;
import commercesyncoffice.org.domain.membergroup.model.MemberGroupId;
import commercesyncoffice.org.global.jwt.JwtUtil;
import commercesyncoffice.org.global.security.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        brandRepository.save(Brand.of(brandCreateDto, admin));
    }

    @Override
    @Transactional(readOnly = true)
    public List<GetBrandListDto> getBrandList(UserDetails userDetails) {

        Admin admin = adminService.getAdminByUsername(userDetails.getUsername());

        return brandRepository.findAllBrandListByAdminId(admin);
    }

    @Override
    public Brand getBrandById(Long brandId) {

        return brandRepository.findById(brandId).orElseThrow(
                () -> new BrandException(ExceptionCode.NOT_FOUND_BRAND)
        );
    }

    @Override
    public void checkBrand(Long brandId) {

        if (!brandRepository.existsById(brandId)) {
            throw new BrandException(ExceptionCode.NOT_FOUND_BRAND);
        }
    }

    @Override
    public boolean existsByIdAndAdminUsername(Long brandId, String username) {
        return brandRepository.existsByIdAndAdminUsername(brandId, username);
    }

    // TODO 리팩토링때 밑 두개 오버로딩이나 오버라이딩 중 택 1 리팩
    @Override
    public void validateBrand(UserDetails userDetails, BrandId brandId) {

        if (((UserDetailsImpl) userDetails).getRole().equals(JwtUtil.ADMIN)) {

            Brand brand = brandRepository.findByIdWithAdmin(brandId.id());
            if (!brand.getAdmin().getUsername().equals(userDetails.getUsername())) {
                throw new BrandException(ExceptionCode.YOUR_NOT_ADMIN_THIS_BRAND);
            }
        } else if (((UserDetailsImpl) userDetails).getRole().equals(JwtUtil.MEMBER)) {

            if (!brandRepository.existsByIdAndMemberUsername(brandId.id(), userDetails.getUsername())) {
                throw new BrandException(ExceptionCode.YOUR_NOT_MEMBER_THIS_BRAND);
            }
        }
    }

    @Override
    public void validateBrand(UserDetails userDetails, MemberGroupId memberGroupId) {

        if (((UserDetailsImpl) userDetails).getRole().equals(JwtUtil.ADMIN)) {

            Brand brand = brandRepository.findByMemberGroupIdWithAdmin(memberGroupId.id());
            if (!brand.getAdmin().getUsername().equals(userDetails.getUsername())) {
                throw new BrandException(ExceptionCode.YOUR_NOT_ADMIN_THIS_BRAND);
            }
        } else if (((UserDetailsImpl) userDetails).getRole().equals(JwtUtil.MEMBER)) {

            if (!brandRepository.existsByMemberGroupIdAndMemberUsername(memberGroupId.id(), userDetails.getUsername())) {
                throw new BrandException(ExceptionCode.YOUR_NOT_MEMBER_THIS_BRAND);
            }
        }
    }
}
