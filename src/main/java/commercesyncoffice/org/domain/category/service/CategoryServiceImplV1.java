package commercesyncoffice.org.domain.category.service;

import commercesyncoffice.org.domain.brand.model.Brand;
import commercesyncoffice.org.domain.brand.model.BrandId;
import commercesyncoffice.org.domain.brand.service.BrandService;
import commercesyncoffice.org.domain.category.exception.CategoryException;
import commercesyncoffice.org.domain.category.message.ExceptionCode;
import commercesyncoffice.org.domain.category.model.Category;
import commercesyncoffice.org.domain.category.dto.request.CategoryCreateDto;
import commercesyncoffice.org.domain.category.dto.response.GetCategoryListDto;
import commercesyncoffice.org.domain.category.repository.CategoryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Primary
@Service
@RequiredArgsConstructor
public class CategoryServiceImplV1 implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final BrandService brandService;

    @Override
    @Transactional
    public void createCategory(UserDetails userDetails, CategoryCreateDto categoryCreateDto, Long brandId) {

        Brand brand = brandService.getBrandById(brandId);

        brandService.validateBrand(userDetails, BrandId.from(brandId));

        categoryRepository.save(Category.of(categoryCreateDto, brand));
    }

    @Override
    @Transactional(readOnly = true)
    public List<GetCategoryListDto> getCategoryList(UserDetails userDetails, Long brandId) {

        Brand brand = brandService.getBrandById(brandId);

        brandService.validateBrand(userDetails, BrandId.from(brandId));

        return categoryRepository.findCategoryListByBrand(brand.getId());
    }

    @Override
    public Category getCategoryByIdAndBrandId(Long categoryId, Long brandId) {

        return categoryRepository.findByIdAndBrandId(categoryId, brandId).orElseThrow(
                () -> new CategoryException(ExceptionCode.NOT_FOUND_CATEGORY)
        );
    }
}
