package commercesyncoffice.org.domain.memberrole.repository;

import commercesyncoffice.org.domain.memberrole.model.MemberRole;
import commercesyncoffice.org.domain.memberrole.model.MemberRoleEnum;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRoleRepository extends JpaRepository<MemberRole, Long> {

    Optional<MemberRole> findByName(MemberRoleEnum memberRoleEnum);
}
