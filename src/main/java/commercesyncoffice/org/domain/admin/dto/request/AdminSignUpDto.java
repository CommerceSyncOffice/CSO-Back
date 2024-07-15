package commercesyncoffice.org.domain.admin.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AdminSignUpDto(
        @NotBlank(message = "아이디를 입력해주세요")
        @Pattern(regexp = "^[a-z0-9]+$", message = "아이디에는 영어 소문자 및 숫자만 입력해주세요")
        @Size(min = 6, max = 20, message = "아이디는 최소 6자 이상, 최대 20자 이하가 되어야 합니다.")
        String username,

        @NotBlank(message = "비밀번호를 입력해주세요")
        @Size(min = 8, max = 20, message = "비밀번호는 최소 8자 이상 20자 이하여야 합니다.")
        String password,

        @NotBlank(message = "이메일을 입력해주세요")
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "유효한 이메일 주소를 입력해주세요")
        @Size(min = 4, max = 60, message = "이메일은 최소 4자 이상 60자 이하여야 합니다.")
        String email,

        String profileImg
) {

}
