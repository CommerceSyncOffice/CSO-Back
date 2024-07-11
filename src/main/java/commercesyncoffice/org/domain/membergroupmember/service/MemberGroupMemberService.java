package commercesyncoffice.org.domain.membergroupmember.service;

import commercesyncoffice.org.domain.membergroupmember.dto.request.MemberGroupMemberRegisterReqDto;
import commercesyncoffice.org.domain.membergroupmember.dto.response.MemberGroupMemberRegisterResDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface MemberGroupMemberService {

    MemberGroupMemberRegisterResDto registerMember(UserDetails userDetails, Long brandId, MemberGroupMemberRegisterReqDto registerDto);

}
