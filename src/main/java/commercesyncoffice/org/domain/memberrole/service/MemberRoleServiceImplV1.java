package commercesyncoffice.org.domain.memberrole.service;

import commercesyncoffice.org.domain.memberrole.exception.MemberRoleException;
import commercesyncoffice.org.domain.memberrole.message.ExceptionCode;
import commercesyncoffice.org.domain.memberrole.model.MemberRole;
import commercesyncoffice.org.domain.memberrole.repository.MemberRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberRoleServiceImplV1 implements MemberRoleService {

    private final MemberRoleRepository memberRoleRepository;

    @Override
    public MemberRole getMemberRoleByMemberRoleId(Long memberRoleId) {

        return memberRoleRepository.findById(memberRoleId).orElseThrow(
                () -> new MemberRoleException(ExceptionCode.NOT_FOUND_MEMBER_ROLE)
        );
    }
}
