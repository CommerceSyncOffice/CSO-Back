package commercesyncoffice.org.domain.category.controller;

import commercesyncoffice.org.domain.category.dto.CategoryCreateDto;
import commercesyncoffice.org.domain.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/brand/{brandId}/category")
    public String createCategory(
            @RequestBody CategoryCreateDto categoryCreateDto,
            @PathVariable Long brandId
    ) {

        return "redirect:/brand/" + brandId + "/category/" + categoryService.createCategory(categoryCreateDto, brandId);
    }
}
