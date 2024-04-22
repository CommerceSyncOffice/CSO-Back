package commercesyncoffice.org.domain.category.service;

import commercesyncoffice.org.domain.brand.Brand;
import commercesyncoffice.org.domain.brand.repository.BrandRepository;
import commercesyncoffice.org.domain.brand.service.BrandService;
import commercesyncoffice.org.domain.category.Category;
import commercesyncoffice.org.domain.category.dto.CategoryCreateDto;
import commercesyncoffice.org.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
@RequiredArgsConstructor
public class CategoryServiceImplV1 implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final BrandService brandService;

    @Override
    public Long createCategory(CategoryCreateDto categoryCreateDto, Long brandId) {

        Brand brand = brandService.getBrandById(brandId);

        return categoryRepository.save(Category.createCategory(categoryCreateDto, brand)).getId();
    }
}
