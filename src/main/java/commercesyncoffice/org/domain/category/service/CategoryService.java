package commercesyncoffice.org.domain.category.service;

import commercesyncoffice.org.domain.category.dto.CategoryCreateDto;

public interface CategoryService {

    Long createCategory(CategoryCreateDto categoryCreateDto, Long brandId);
}
