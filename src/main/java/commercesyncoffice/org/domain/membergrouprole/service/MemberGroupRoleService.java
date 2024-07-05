package commercesyncoffice.org.domain.membergrouprole.service;

import commercesyncoffice.org.domain.membergrouprole.dto.request.MemberGroupRoleEditReqDto;
import commercesyncoffice.org.domain.membergrouprole.dto.response.MemberGroupRoleResDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface MemberGroupRoleService {

    MemberGroupRoleResDto editMemberGroupRole(UserDetails userDetails, Long memberGroupId, MemberGroupRoleEditReqDto memberGroupRoleEditReqDto);
}
