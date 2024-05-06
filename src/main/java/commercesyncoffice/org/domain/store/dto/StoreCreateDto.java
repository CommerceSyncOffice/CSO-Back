package commercesyncoffice.org.domain.store.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record StoreCreateDto(
        @NotBlank(message = "스토어명을 입력해주세요")
        @Size(min = 1, max = 50, message = "스토어명은 최소 1글자, 최대 50글자로 설정해야 합니다.")
        String name,

        @NotBlank(message = "스토어 주소를 입력해주세요")
        String address
) {

}
