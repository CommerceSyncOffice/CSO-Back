package commercesyncoffice.org.domain.admin.repository;

import commercesyncoffice.org.domain.admin.model.Admin;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Query("SELECT a"
            + " FROM Admin a"
            + " WHERE a.username = :username")
    Optional<Admin> findByUsername(@Param("username") String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

}
