package commercesyncoffice.org.domain.category.controller;

import static commercesyncoffice.org.domain.category.message.SuccessMessage.SUCCESS_CREATE_CATEGORY;
import static commercesyncoffice.org.domain.category.message.SuccessMessage.SUCCESS_GET_CATEGORY_LIST;
import static commercesyncoffice.org.global.response.SuccessResponse.success;

import commercesyncoffice.org.domain.category.dto.request.CategoryCreateDto;
import commercesyncoffice.org.domain.category.service.CategoryService;
import commercesyncoffice.org.global.response.CommonResponse;
import commercesyncoffice.org.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController implements CategoryControllerSwagger {

    private final CategoryService categoryService;

    @PostMapping("/brand/{brandId}/category")
    public ResponseEntity<? extends CommonResponse> createCategory(
            @RequestBody @Valid CategoryCreateDto categoryCreateDto,
            @PathVariable Long brandId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        categoryService.createCategory(userDetails, categoryCreateDto, brandId);

        return ResponseEntity.status(SUCCESS_CREATE_CATEGORY.getHttpStatus())
                             .body(success(SUCCESS_CREATE_CATEGORY.getMessage()));
    }

    @GetMapping("/brand/{brandId}/category")
    public ResponseEntity<? extends CommonResponse> getCategory(
            @PathVariable Long brandId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        return ResponseEntity.status(SUCCESS_GET_CATEGORY_LIST.getHttpStatus())
                             .body(success(SUCCESS_GET_CATEGORY_LIST.getMessage(), categoryService.getCategoryList(userDetails, brandId)));
    }
}
