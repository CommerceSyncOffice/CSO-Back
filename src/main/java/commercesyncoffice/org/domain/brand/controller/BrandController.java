package commercesyncoffice.org.domain.brand.controller;

import static commercesyncoffice.org.domain.brand.message.SuccessMessage.SUCCESS_CREATE_BRAND;
import static commercesyncoffice.org.domain.brand.message.SuccessMessage.SUCCESS_GET_BRAND_LIST;
import static commercesyncoffice.org.global.response.SuccessResponse.success;

import commercesyncoffice.org.domain.brand.dto.request.BrandCreateDto;
import commercesyncoffice.org.domain.brand.service.BrandService;
import commercesyncoffice.org.global.response.CommonResponse;
import commercesyncoffice.org.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BrandController implements BrandControllerSwagger {

    private final BrandService brandService;

    @PostMapping("/brand")
    public ResponseEntity<? extends CommonResponse> createBrand(
            @RequestBody @Valid BrandCreateDto brandCreateDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        brandService.createBrand(brandCreateDto, userDetails);

        return ResponseEntity.status(SUCCESS_CREATE_BRAND.getHttpStatus())
                             .body(success(SUCCESS_CREATE_BRAND.getMessage()));
    }

    @GetMapping("/brand")
    public ResponseEntity<? extends CommonResponse> getBrandList(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        return ResponseEntity.status(SUCCESS_GET_BRAND_LIST.getHttpStatus())
                             .body(success(SUCCESS_GET_BRAND_LIST.getMessage(),brandService.getBrandList(userDetails)));
    }
}
