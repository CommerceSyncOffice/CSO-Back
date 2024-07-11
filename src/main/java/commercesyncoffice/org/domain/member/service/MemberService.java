package commercesyncoffice.org.domain.member.service;

import commercesyncoffice.org.domain.member.model.Member;
import commercesyncoffice.org.domain.member.dto.request.MemberLoginDto;
import commercesyncoffice.org.domain.member.dto.request.MemberPasswordChangeDto;
import commercesyncoffice.org.domain.member.dto.request.MemberSignUpDto;
import commercesyncoffice.org.domain.member.dto.response.MemberSignUpResponseDto;
import commercesyncoffice.org.global.security.UserDetailsImpl;

public interface MemberService {

    MemberSignUpResponseDto signUp(Long brandId, MemberSignUpDto memberSignUpDto, UserDetailsImpl userDetails);

    String login(Long brandId, MemberLoginDto memberLoginDto);

    void changePassword(MemberPasswordChangeDto memberPasswordChangeDto, UserDetailsImpl userDetails);

    Member getMemberByMemberId(Long memberId);
//    Member getMemberByUsername(String username);
}
