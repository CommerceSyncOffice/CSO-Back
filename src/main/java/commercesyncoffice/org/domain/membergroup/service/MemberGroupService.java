package commercesyncoffice.org.domain.membergroup.service;

import commercesyncoffice.org.domain.membergroup.model.MemberGroup;
import commercesyncoffice.org.domain.membergroup.dto.request.MemberGroupCreateDto;
import commercesyncoffice.org.domain.membergroup.dto.response.MemberGroupResDto;
import commercesyncoffice.org.global.security.UserDetailsImpl;

public interface MemberGroupService {

    MemberGroup getMemberGroupByMemberGroupId(Long memberGroupId);

    MemberGroupResDto createMemberGroup(UserDetailsImpl userDetails, Long brandId, MemberGroupCreateDto memberGroupCreateDto);
}
