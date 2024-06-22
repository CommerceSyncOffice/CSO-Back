package commercesyncoffice.org.domain.member.service;

import commercesyncoffice.org.domain.member.dto.MemberLoginDto;
import commercesyncoffice.org.domain.member.dto.MemberSignUpDto;
import commercesyncoffice.org.domain.member.dto.MemberSignUpResponseDto;
import commercesyncoffice.org.global.security.UserDetailsImpl;

public interface MemberService {

    MemberSignUpResponseDto signUp(Long brandId, MemberSignUpDto memberSignUpDto, UserDetailsImpl userDetails);

    String login(Long brandId, MemberLoginDto memberLoginDto);
}
