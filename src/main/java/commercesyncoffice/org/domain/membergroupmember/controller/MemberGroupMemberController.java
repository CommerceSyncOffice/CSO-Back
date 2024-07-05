package commercesyncoffice.org.domain.membergroupmember.controller;

import commercesyncoffice.org.domain.membergroupmember.dto.MemberGroupMemberRegisterReqDto;
import commercesyncoffice.org.domain.membergroupmember.dto.MemberGroupMemberRegisterResDto;
import commercesyncoffice.org.domain.membergroupmember.response.MemberGroupMemberResponse;
import commercesyncoffice.org.domain.membergroupmember.response.MemberGroupMemberResponseEnum;
import commercesyncoffice.org.domain.membergroupmember.service.MemberGroupMemberService;
import commercesyncoffice.org.global.response.CommonResponse;
import commercesyncoffice.org.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberGroupMemberController {

    private final MemberGroupMemberService memberGroupMemberService;


    @PostMapping("/brand/{brandId}/member_group/register")
    public ResponseEntity<? extends CommonResponse> registerMember(
            @PathVariable Long brandId,
            @RequestBody MemberGroupMemberRegisterReqDto memberGroupMemberRegisterReqDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        MemberGroupMemberRegisterResDto responseDto = memberGroupMemberService.registerMember(
                userDetails, brandId,
                memberGroupMemberRegisterReqDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new MemberGroupMemberResponse(
                MemberGroupMemberResponseEnum.RESISTER_MEMBER, responseDto));
    }
}
