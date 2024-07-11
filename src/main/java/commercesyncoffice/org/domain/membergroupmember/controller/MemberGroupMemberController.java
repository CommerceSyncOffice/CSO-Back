package commercesyncoffice.org.domain.membergroupmember.controller;

import static commercesyncoffice.org.domain.membergroupmember.message.SuccessMessage.SUCCESS_RESISTER_MEMBER;
import static commercesyncoffice.org.global.response.SuccessResponse.success;

import commercesyncoffice.org.domain.membergroupmember.dto.request.MemberGroupMemberRegisterReqDto;
import commercesyncoffice.org.domain.membergroupmember.service.MemberGroupMemberService;
import commercesyncoffice.org.global.response.CommonResponse;
import commercesyncoffice.org.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
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

        return ResponseEntity.status(SUCCESS_RESISTER_MEMBER.getHttpStatus())
                .body(success(SUCCESS_RESISTER_MEMBER.getMessage(), memberGroupMemberService.registerMember(userDetails, brandId, memberGroupMemberRegisterReqDto)));
    }
}
