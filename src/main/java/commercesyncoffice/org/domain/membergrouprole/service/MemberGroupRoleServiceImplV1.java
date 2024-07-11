package commercesyncoffice.org.domain.membergrouprole.service;

import commercesyncoffice.org.domain.brand.service.BrandService;
import commercesyncoffice.org.domain.membergroup.model.MemberGroup;
import commercesyncoffice.org.domain.membergroup.service.MemberGroupService;
import commercesyncoffice.org.domain.membergrouprole.model.MemberGroupRole;
import commercesyncoffice.org.domain.membergrouprole.dto.request.MemberGroupRoleEditReqDto;
import commercesyncoffice.org.domain.membergrouprole.dto.response.MemberGroupRoleResDto;
import commercesyncoffice.org.domain.membergrouprole.repository.MemberGroupRoleRepository;
import commercesyncoffice.org.domain.memberrole.MemberRole;
import commercesyncoffice.org.domain.memberrole.service.MemberRoleService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberGroupRoleServiceImplV1 implements MemberGroupRoleService {

    private final MemberGroupRoleRepository memberGroupRoleRepository;
    private final MemberGroupService memberGroupService;
    private final MemberRoleService memberRoleService;
    private final BrandService brandService;


    @Override
    @Transactional
    public MemberGroupRoleResDto editMemberGroupRole(
            UserDetails userDetails,
            Long memberGroupId,
            MemberGroupRoleEditReqDto memberGroupRoleEditReqDto
    ) {

        MemberGroup memberGroup = memberGroupService.getMemberGroupByMemberGroupId(memberGroupId);
        brandService.validateBrandByMemberGroupId(userDetails, memberGroupId);
        memberGroupRoleRepository.deleteAllByMemberGroupId(memberGroupId);

        for (Long memberRoleId : memberGroupRoleEditReqDto.memberRoleId()) {
            MemberRole memberRole = memberRoleService.getMemberRoleByMemberRoleId(memberRoleId);
            memberGroupRoleRepository.save(MemberGroupRole.of(memberGroup, memberRole));
        }

        List<String> memberRoles = memberGroupRoleRepository.findAllMemberRoleByMemberGroupId(
                memberGroupId);

        return new MemberGroupRoleResDto(memberGroupId, memberRoles);
    }
}
