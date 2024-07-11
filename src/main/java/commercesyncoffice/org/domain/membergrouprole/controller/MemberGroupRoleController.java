package commercesyncoffice.org.domain.membergrouprole.controller;

import static commercesyncoffice.org.domain.membergrouprole.message.SuccessMessage.SUCCESS_EDIT_MEMBER_GROUP_ROLE;
import static commercesyncoffice.org.global.response.SuccessResponse.success;

import commercesyncoffice.org.domain.membergrouprole.dto.request.MemberGroupRoleEditReqDto;
import commercesyncoffice.org.domain.membergrouprole.service.MemberGroupRoleService;
import commercesyncoffice.org.global.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberGroupRoleController {

    private final MemberGroupRoleService memberGroupRoleService;

    @PostMapping("/brand/member_group/{memberGroupId}/role")
    public ResponseEntity<? extends CommonResponse> editMemberGroupRole(
            @PathVariable Long memberGroupId,
            @RequestBody MemberGroupRoleEditReqDto memberGroupRoleEditReqDto,
            @AuthenticationPrincipal UserDetails userDetails
    ) {

        return ResponseEntity.status(SUCCESS_EDIT_MEMBER_GROUP_ROLE.getHttpStatus())
                             .body(success(SUCCESS_EDIT_MEMBER_GROUP_ROLE.getMessage(), memberGroupRoleService.editMemberGroupRole(userDetails, memberGroupId, memberGroupRoleEditReqDto)));
    }
}
