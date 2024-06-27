package commercesyncoffice.org.domain.brand.repository;

import commercesyncoffice.org.domain.admin.Admin;
import commercesyncoffice.org.domain.brand.Brand;
import commercesyncoffice.org.domain.brand.dto.GetBrandListDto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    @Query("SELECT b"
            + " FROM Brand b"
            + " WHERE b.id = :id")
    Optional<Brand> findById(@Param("id") Long id);

    @Query("SELECT"
            + " new commercesyncoffice.org.domain.brand.dto.GetBrandListDto(b.name, b.description)"
            + " FROM"
            + " Brand b"
            + " WHERE"
            + " b.admin = :admin"
    )
    List<GetBrandListDto> findAllBrandListByAdminId(@Param("admin") Admin admin);

    @Query("SELECT COUNT(b.id) > 0"
            + " FROM Brand b"
            + " JOIN Admin a ON a.id = b.admin.id"
            + " WHERE b.id = :brandId AND a.username = :username")
    boolean existsByIdAndAdminUsername(@Param("brandId") Long brandId, @Param("username") String username);
}
