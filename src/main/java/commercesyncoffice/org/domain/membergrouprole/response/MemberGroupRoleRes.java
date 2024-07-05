package commercesyncoffice.org.domain.membergrouprole.response;

import commercesyncoffice.org.domain.membergrouprole.dto.response.MemberGroupRoleResDto;
import commercesyncoffice.org.global.response.CommonResponse;
import lombok.Getter;

@Getter
public class MemberGroupRoleRes extends CommonResponse {

    private final MemberGroupRoleResDto memberGroupRoleResDto;

    public MemberGroupRoleRes(String message, MemberGroupRoleResDto memberGroupRoleResDto) {
        super(message);
        this.memberGroupRoleResDto = memberGroupRoleResDto;
    }
}
