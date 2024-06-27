package commercesyncoffice.org.domain.category.service;

import commercesyncoffice.org.domain.category.Category;
import commercesyncoffice.org.domain.category.dto.CategoryCreateDto;
import commercesyncoffice.org.domain.category.dto.GetCategoryListDto;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;

public interface CategoryService {

    Long createCategory(UserDetails userDetails, CategoryCreateDto categoryCreateDto, Long brandId);

    List<GetCategoryListDto> getCategoryList(UserDetails userDetails, Long brandId);

    Category getCategoryByIdAndBrandId(Long categoryId, Long brandId);
}
