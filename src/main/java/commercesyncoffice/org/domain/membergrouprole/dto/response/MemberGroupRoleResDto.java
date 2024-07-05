package commercesyncoffice.org.domain.membergrouprole.dto.response;

import java.util.List;

public record MemberGroupRoleResDto(
        Long memberGroupId,
        List<String> memberRoleList
) {

}
