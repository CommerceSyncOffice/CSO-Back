package commercesyncoffice.org.domain.membergroup.response;

import commercesyncoffice.org.domain.membergroup.dto.request.MemberGroupCreateDto;
import commercesyncoffice.org.domain.membergroup.dto.response.MemberGroupResDto;
import commercesyncoffice.org.global.response.CommonResponse;

public class MemberGroupResponse extends CommonResponse {

    private final Long memberGroupId;
    private final String memberGroupName;
    private final String memberGroupDescription;

    public MemberGroupResponse(String message, MemberGroupResDto responseDto) {
        super(message);
        this.memberGroupId = responseDto.id();
        this.memberGroupName = responseDto.name();
        this.memberGroupDescription = responseDto.description();
    }
}
