package commercesyncoffice.org.domain.category.service;

import commercesyncoffice.org.domain.category.model.Category;
import commercesyncoffice.org.domain.category.dto.request.CategoryCreateDto;
import commercesyncoffice.org.domain.category.dto.response.GetCategoryListDto;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;

public interface CategoryService {

    void createCategory(UserDetails userDetails, CategoryCreateDto categoryCreateDto, Long brandId);

    List<GetCategoryListDto> getCategoryList(UserDetails userDetails, Long brandId);

    Category getCategoryByIdAndBrandId(Long categoryId, Long brandId);
}
