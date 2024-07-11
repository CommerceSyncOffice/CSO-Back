package commercesyncoffice.org.domain.membergroupmember.service;

import commercesyncoffice.org.domain.brand.service.BrandService;
import commercesyncoffice.org.domain.member.model.Member;
import commercesyncoffice.org.domain.member.service.MemberService;
import commercesyncoffice.org.domain.membergroup.model.MemberGroup;
import commercesyncoffice.org.domain.membergroup.service.MemberGroupService;
import commercesyncoffice.org.domain.membergroupmember.MemberGroupMember;
import commercesyncoffice.org.domain.membergroupmember.dto.MemberGroupMemberRegisterReqDto;
import commercesyncoffice.org.domain.membergroupmember.dto.MemberGroupMemberRegisterResDto;
import commercesyncoffice.org.domain.membergroupmember.repository.MemberGroupMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberGroupMemberServiceImplV1 implements MemberGroupMemberService {

    private final MemberGroupMemberRepository memberGroupMemberRepository;
    private final MemberGroupService memberGroupService;
    private final MemberService memberService;
    private final BrandService brandService;

    @Override
    @Transactional
    public MemberGroupMemberRegisterResDto registerMember(UserDetails userDetails, Long brandId, MemberGroupMemberRegisterReqDto registerDto) {

        brandService.validateBrand(userDetails, brandId);
        Member member = memberService.getMemberByMemberId(registerDto.memberId());
        MemberGroup memberGroup = memberGroupService.getMemberGroupByMemberGroupId(registerDto.memberGroupId());

        MemberGroupMember saved = memberGroupMemberRepository.save(
                MemberGroupMember.createMemberGroupMember(member, memberGroup));

        return new MemberGroupMemberRegisterResDto(saved.getId(), memberGroup.getId(), member.getId());
    }
}
