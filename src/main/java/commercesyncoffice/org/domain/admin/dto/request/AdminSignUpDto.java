package commercesyncoffice.org.domain.admin.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "AdminSignUpDto")
public record AdminSignUpDto(

        @Schema(description = "아이디", examples = "username1004")
        @NotBlank(message = "아이디를 입력해주세요")
        @Pattern(regexp = "^[a-z0-9]+$", message = "아이디에는 영어 소문자 및 숫자만 입력해주세요")
        @Size(min = 6, max = 20, message = "아이디는 최소 6자 이상, 최대 20자 이하가 되어야 합니다.")
        String username,

        @Schema(description = "비밀번호", examples = "password")
        @NotBlank(message = "비밀번호를 입력해주세요")
        @Size(min = 8, max = 20, message = "비밀번호는 최소 8자 이상 20자 이하여야 합니다.")
        String password,

        @Schema(description = "이메일", examples = "CSOProject@gmail.com")
        @NotBlank(message = "이메일을 입력해주세요")
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "유효한 이메일 주소를 입력해주세요")
        @Size(min = 4, max = 60, message = "이메일은 최소 4자 이상 60자 이하여야 합니다.")
        String email,

        @Schema(description = "프로필 이미지 링크", examples = "https://www.aws.com/s3/cso-project/images/wonow.png")
        String profileImg
) {

}
