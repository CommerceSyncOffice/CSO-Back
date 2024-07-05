package commercesyncoffice.org.domain.brand.service;

import commercesyncoffice.org.domain.admin.Admin;
import commercesyncoffice.org.domain.admin.service.AdminService;
import commercesyncoffice.org.domain.brand.Brand;
import commercesyncoffice.org.domain.brand.dto.BrandCreateDto;
import commercesyncoffice.org.domain.brand.dto.GetBrandListDto;
import commercesyncoffice.org.domain.brand.repository.BrandRepository;
import commercesyncoffice.org.global.exception.CustomException;
import commercesyncoffice.org.global.exception.ExceptionCode;
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

        brandRepository.save(Brand.createBrand(brandCreateDto, admin));
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

//    @Override
//    public void validateBrand(Member member, Long brandId) {
//
//        if (!brandRepository.existsByIdAndMemberUsername(brandId, member.getId())) {
//            throw new CustomException(ExceptionCode.YOUR_NOT_MEMBER_THIS_BRAND);
//        }
//    }
//
//    @Override
//    public void validateBrand(Admin admin, Long brandId) {
//
//        Brand brand = brandRepository.findByIdWithAdmin(brandId);
//
//        if (!brand.getAdmin().getId().equals(admin.getId())) {
//            throw new CustomException(ExceptionCode.YOUR_NOT_ADMIN_THIS_BRAND);
//        }
//    }

    //TODO 리팩토링때 밑 두개 오버로딩이나 오버라이딩 중 택 1 리팩
    @Override
    public void validateBrand(UserDetails userDetails, Long brandId) {

        if (((UserDetailsImpl) userDetails).getRole().equals(JwtUtil.ADMIN)) {

            Brand brand = brandRepository.findByIdWithAdmin(brandId);
            if (!brand.getAdmin().getUsername().equals(userDetails.getUsername())) {
                throw new CustomException(ExceptionCode.YOUR_NOT_ADMIN_THIS_BRAND);
            }
        } else if (((UserDetailsImpl) userDetails).getRole().equals(JwtUtil.MEMBER)) {

            if (!brandRepository.existsByIdAndMemberUsername(brandId, userDetails.getUsername())) {
                throw new CustomException(ExceptionCode.YOUR_NOT_MEMBER_THIS_BRAND);
            }
        }
    }

    @Override
    public void validateBrandByMemberGroupId(UserDetails userDetails, Long memberGroupId) {

        if (((UserDetailsImpl) userDetails).getRole().equals(JwtUtil.ADMIN)) {

            Brand brand = brandRepository.findByMemberGroupIdWithAdmin(memberGroupId);
            if (!brand.getAdmin().getUsername().equals(userDetails.getUsername())) {
                throw new CustomException(ExceptionCode.YOUR_NOT_ADMIN_THIS_BRAND);
            }
        } else if (((UserDetailsImpl) userDetails).getRole().equals(JwtUtil.MEMBER)) {

            if (!brandRepository.existsByMemberGroupIdAndMemberUsername(memberGroupId, userDetails.getUsername())) {
                throw new CustomException(ExceptionCode.YOUR_NOT_MEMBER_THIS_BRAND);
            }
        }
    }
}
