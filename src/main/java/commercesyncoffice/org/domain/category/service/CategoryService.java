package commercesyncoffice.org.domain.category.service;

import commercesyncoffice.org.domain.category.dto.CategoryCreateDto;
import commercesyncoffice.org.domain.category.dto.GetCategoryListDto;
import java.util.List;

public interface CategoryService {

    Long createCategory(CategoryCreateDto categoryCreateDto, Long brandId);

    List<GetCategoryListDto> getCategoryList(Long brandId);
}
