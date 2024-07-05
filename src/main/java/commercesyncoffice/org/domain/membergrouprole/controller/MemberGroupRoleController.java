package commercesyncoffice.org.domain.membergrouprole.controller;

import commercesyncoffice.org.domain.membergrouprole.dto.request.MemberGroupRoleEditReqDto;
import commercesyncoffice.org.domain.membergrouprole.dto.response.MemberGroupRoleResDto;
import commercesyncoffice.org.domain.membergrouprole.dto.response.MemberGroupRoleResEnum;
import commercesyncoffice.org.domain.membergrouprole.response.MemberGroupRoleRes;
import commercesyncoffice.org.domain.membergrouprole.service.MemberGroupRoleService;
import commercesyncoffice.org.global.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

        MemberGroupRoleResDto memberGroupRoleResDto = memberGroupRoleService.editMemberGroupRole(
                userDetails, memberGroupId, memberGroupRoleEditReqDto);
        MemberGroupRoleRes memberGroupRoleRes = new MemberGroupRoleRes(MemberGroupRoleResEnum.MEMBER_GROUP_ROLE_CREATED.getMessage(), memberGroupRoleResDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(memberGroupRoleRes);
    }
}
