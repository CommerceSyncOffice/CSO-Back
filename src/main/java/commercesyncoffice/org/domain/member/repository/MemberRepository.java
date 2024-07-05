package commercesyncoffice.org.domain.member.repository;

import commercesyncoffice.org.domain.member.Member;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m"
            + " FROM Member m"
            + " WHERE m.username = :username AND m.brand.id = :brandId")
    Optional<Member> findByUsernameAndBrandId(@Param("username") String username,
            @Param("brandId") Long brandId);

    @Query("SELECT mr.name"
            + " FROM Member m"
            + " JOIN Brand b ON b.id = m.brand.id"
            + " JOIN MemberGroupMember mgm ON m.id = mgm.member.id"
            + " JOIN MemberGroup mg ON mgm.memberGroup.id = mg.id"
            + " JOIN MemberGroupRole mgr ON mgr.memberGroup.id = mg.id"
            + " JOIN MemberRole mr ON mr.id = mgr.memberRole.id"
            + " WHERE m.username = :username AND m.brand.id = :brandId")
    List<String> findMemberRolesByUsernameAndBrandId(@Param("username") String username,
            @Param("brandId") Long brandId);

    boolean existsByUsername(String username);

    Optional<Member> findByUsername(String username);
}
