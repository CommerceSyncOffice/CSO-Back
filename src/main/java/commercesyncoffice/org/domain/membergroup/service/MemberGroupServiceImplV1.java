package commercesyncoffice.org.domain.membergroup.service;

import commercesyncoffice.org.domain.membergroup.MemberGroup;
import commercesyncoffice.org.domain.membergroup.repository.MemberGroupRepository;
import commercesyncoffice.org.global.exception.CustomException;
import commercesyncoffice.org.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberGroupServiceImplV1 implements MemberGroupService {

    private final MemberGroupRepository memberGroupRepository;


    @Override
    public MemberGroup getMemberGroupByMemberGroupId(Long memberGroupId) {

        return memberGroupRepository.findById(memberGroupId).orElseThrow(
                () -> new CustomException(ExceptionCode.NOT_FOUND_MEMBER_GROUP)
        );
    }
}
