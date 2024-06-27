package commercesyncoffice.org.domain.category.controller;

import commercesyncoffice.org.domain.category.dto.CategoryCreateDto;
import commercesyncoffice.org.domain.category.dto.GetCategoryListDto;
import commercesyncoffice.org.domain.category.service.CategoryService;
import commercesyncoffice.org.global.security.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
            @PathVariable Long brandId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        categoryService.createCategory(userDetails, categoryCreateDto, brandId);

        return "redirect:/brand/" + brandId + "/category";
    }

    @GetMapping("/brand/{brandId}/category")
    public ResponseEntity<List<GetCategoryListDto>> getCategory(
            @PathVariable Long brandId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        return ResponseEntity.ok().body(categoryService.getCategoryList(userDetails, brandId));
    }
}
