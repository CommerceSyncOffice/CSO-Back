package commercesyncoffice.org.domain.membergroup.controller;

import commercesyncoffice.org.domain.membergroup.dto.request.MemberGroupCreateDto;
import commercesyncoffice.org.domain.membergroup.dto.response.MemberGroupResDto;
import commercesyncoffice.org.domain.membergroup.dto.response.MemberGroupResponseEnum;
import commercesyncoffice.org.domain.membergroup.response.MemberGroupResponse;
import commercesyncoffice.org.domain.membergroup.service.MemberGroupService;
import commercesyncoffice.org.global.response.CommonResponse;
import commercesyncoffice.org.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
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
public class MemberGroupController {

    private final MemberGroupService memberGroupService;

    @PostMapping("/brand/{brandId}/member_group")
    public ResponseEntity<? extends CommonResponse> createMemberGroup(
            @PathVariable Long brandId,
            @RequestBody @Valid MemberGroupCreateDto memberGroupCreateDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        MemberGroupResDto memberGroupResDto = memberGroupService.createMemberGroup(userDetails, brandId, memberGroupCreateDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(new MemberGroupResponse(MemberGroupResponseEnum.CREATED.getMessage(), memberGroupResDto));
    }

}
