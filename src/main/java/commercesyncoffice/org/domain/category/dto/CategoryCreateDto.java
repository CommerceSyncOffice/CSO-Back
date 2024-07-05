package commercesyncoffice.org.domain.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CategoryCreateDto(

        @NotBlank
        @Pattern(regexp = "^[a-z가-힣0-9]", message = "카테고리명은 한/영 숫자만 입력할 수 있습니다.")
        @Size(min = 1, max = 50, message = "카테고리명은 최소 1글자, 최대 50글자 까지 입력할 수 있습니다.")
        String name,
        String description
) {

}
