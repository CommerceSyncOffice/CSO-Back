package commercesyncoffice.org.domain.membergroup.controller;

import static commercesyncoffice.org.domain.membergroup.message.SuccessMessage.SUCCESS_CREATE_MEMBER_GROUP;

import commercesyncoffice.org.domain.membergroup.dto.request.MemberGroupCreateDto;
import commercesyncoffice.org.domain.membergroup.dto.response.MemberGroupResDto;
import commercesyncoffice.org.domain.membergroup.service.MemberGroupService;
import commercesyncoffice.org.global.response.CommonResponse;
import commercesyncoffice.org.global.response.SuccessResponse;
import commercesyncoffice.org.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberGroupController {

    private final MemberGroupService memberGroupService;

    @PostMapping("/brand/{brandId}/member_group")
    public ResponseEntity<? extends CommonResponse> createMemberGroup(
            @PathVariable Long brandId,
            @RequestBody @Valid MemberGroupCreateDto memberGroupCreateDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        MemberGroupResDto memberGroupResDto = memberGroupService.createMemberGroup(userDetails, brandId, memberGroupCreateDto);

        return ResponseEntity.status(SUCCESS_CREATE_MEMBER_GROUP.getHttpStatus())
                             .body(SuccessResponse.success(SUCCESS_CREATE_MEMBER_GROUP.getMessage(), memberGroupResDto));
    }

}
