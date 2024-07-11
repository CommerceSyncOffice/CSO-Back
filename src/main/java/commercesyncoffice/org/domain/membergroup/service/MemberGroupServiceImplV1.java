package commercesyncoffice.org.domain.membergroup.service;

import commercesyncoffice.org.domain.brand.model.Brand;
import commercesyncoffice.org.domain.brand.service.BrandService;
import commercesyncoffice.org.domain.membergroup.exception.MemberGroupException;
import commercesyncoffice.org.domain.membergroup.message.ExceptionCode;
import commercesyncoffice.org.domain.membergroup.model.MemberGroup;
import commercesyncoffice.org.domain.membergroup.dto.request.MemberGroupCreateDto;
import commercesyncoffice.org.domain.membergroup.dto.response.MemberGroupResDto;
import commercesyncoffice.org.domain.membergroup.repository.MemberGroupRepository;
import commercesyncoffice.org.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberGroupServiceImplV1 implements MemberGroupService {

    private final MemberGroupRepository memberGroupRepository;
    private final BrandService brandService;


    @Override
    @Transactional
    public MemberGroupResDto createMemberGroup(UserDetailsImpl userDetails, Long brandId,
            MemberGroupCreateDto memberGroupCreateDto) {

        brandService.validateBrand(userDetails, brandId);
        Brand brand = brandService.getBrandById(brandId);

        MemberGroup savedMemberGroup = memberGroupRepository.save(
                MemberGroup.createMemberGroup(memberGroupCreateDto, brand));

        return new MemberGroupResDto(savedMemberGroup.getId(), savedMemberGroup.getName(), savedMemberGroup.getDescription());
    }

    @Override
    public MemberGroup getMemberGroupByMemberGroupId(Long memberGroupId) {

        return memberGroupRepository.findById(memberGroupId).orElseThrow(
                () -> new MemberGroupException(ExceptionCode.NOT_FOUND_MEMBER_GROUP)
        );
    }
}
