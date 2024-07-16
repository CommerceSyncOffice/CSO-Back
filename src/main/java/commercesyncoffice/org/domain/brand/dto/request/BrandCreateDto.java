package commercesyncoffice.org.domain.brand.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record BrandCreateDto(

        @NotBlank(message = "브랜드 명을 입력해주세요.")
        @Pattern(regexp = "^[a-z가-힣0-9\\s]+$", message = "브랜드명은 한/영 숫자만 입력할 수 있습니다.")
        @Size(min = 1, max = 50, message = "브랜드명은 최소 1글자, 최대 50글자 까지 입력할 수 있습니다.")
        String name,

        String description
) {

}
