package commercesyncoffice.org.domain.brand.controller;

import commercesyncoffice.org.domain.brand.dto.BrandCreateDto;
import commercesyncoffice.org.domain.brand.dto.GetBrandListDto;
import commercesyncoffice.org.domain.brand.service.BrandService;
import commercesyncoffice.org.global.security.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @PostMapping("/brand")
    public String createBrand(
            @RequestBody BrandCreateDto brandCreateDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        brandService.createBrand(brandCreateDto, userDetails);

        return "redirect:/brand";
    }

    @ResponseBody
    @GetMapping("/brand")
    public ResponseEntity<List<GetBrandListDto>> getBrandList() {

        Long adminId = 1L;
        return ResponseEntity.ok().body(brandService.getBrandList(adminId));
    }
}
