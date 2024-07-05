package commercesyncoffice.org.domain.membergrouprole.repository;

import commercesyncoffice.org.domain.membergrouprole.MemberGroupRole;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberGroupRoleRepository extends JpaRepository<MemberGroupRole, Long> {

    void deleteAllByMemberGroupId(Long memberGroupId);

    @Query("SELECT mr.name"
            + " FROM MemberGroupRole mgr"
            + " JOIN MemberGroup mg ON mg.id = mgr.memberGroup.id"
            + " JOIN MemberRole mr ON mr.id = mgr.memberRole.id"
            + " WHERE mg.id = :memberGroupId")
    List<String> findAllMemberRoleByMemberGroupId(@Param("memberGroupId") Long memberGroupId);
}
