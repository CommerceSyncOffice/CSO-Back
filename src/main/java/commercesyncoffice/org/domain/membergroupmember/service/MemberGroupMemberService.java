package commercesyncoffice.org.domain.membergroupmember.service;

import commercesyncoffice.org.domain.membergroupmember.dto.MemberGroupMemberRegisterReqDto;
import commercesyncoffice.org.domain.membergroupmember.dto.MemberGroupMemberRegisterResDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface MemberGroupMemberService {

    MemberGroupMemberRegisterResDto registerMember(UserDetails userDetails, Long brandId, MemberGroupMemberRegisterReqDto registerDto);

}
