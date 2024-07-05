package commercesyncoffice.org.domain.memberrole.service;

import commercesyncoffice.org.domain.memberrole.MemberRole;
import commercesyncoffice.org.domain.memberrole.repository.MemberRoleRepository;
import commercesyncoffice.org.global.exception.CustomException;
import commercesyncoffice.org.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberRoleServiceImplV1 implements MemberRoleService {

    private final MemberRoleRepository memberRoleRepository;

    @Override
    public MemberRole getMemberRoleByMemberRoleId(Long memberRoleId) {

        return memberRoleRepository.findById(memberRoleId).orElseThrow(
                () -> new CustomException(ExceptionCode.NOT_FOUND_MEMBER_ROLE)
        );
    }
}
