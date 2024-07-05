package commercesyncoffice.org.domain.membergroupmember.response;

import commercesyncoffice.org.domain.membergroupmember.dto.MemberGroupMemberRegisterResDto;
import commercesyncoffice.org.global.response.CommonResponse;
import lombok.Getter;

@Getter
public class MemberGroupMemberResponse extends CommonResponse {

    private final Long memberGroupMemberId;
    private final Long memberGroupId;
    private final Long memberId;

    public MemberGroupMemberResponse(MemberGroupMemberResponseEnum responseEnum, MemberGroupMemberRegisterResDto responseDto) {
        super(responseEnum.getMessage());
        this.memberGroupMemberId = responseDto.memberGroupMemberId();
        this.memberGroupId = responseDto.memberGroupId();
        this.memberId = responseDto.memberId();
    }
}
