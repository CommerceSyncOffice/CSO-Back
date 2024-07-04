package commercesyncoffice.org.domain.membergroup.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MemberGroupCreateDto(

        @NotBlank(message = "그룹명을 입력해주세요")
        @Size(min = 1, max = 50, message = "그룹명은 최소 2글자, 최대 20글자로 설정해야 합니다.")
        String name,

        String description
) {

}
