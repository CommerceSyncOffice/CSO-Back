package commercesyncoffice.org.domain.itemserial.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ItemSerialCreateDto (

        @NotBlank(message = "시리얼 번호를 입력해주세요.")
        @Size(min = 1, max = 20, message = "시리얼 번호는 최소 1글자, 최대 20 글자만 등록할 수 있습니다.")
        @Pattern(regexp = "^[A-Z0-9][A-Z0-9-]*[A-Z0-9]$", message = "시리얼 번호는 다음 조건을 충족해야 합니다:\n"
                + "\n"
                + "영대문자(A-Z) 또는 숫자(0-9)로 시작해야 합니다.\n"
                + "중간에는 영대문자, 숫자, 또는 하이픈(-)이 올 수 있습니다.\n"
                + "마지막에는 영대문자 또는 숫자로 끝나야 합니다.")
        String serial
){

}
