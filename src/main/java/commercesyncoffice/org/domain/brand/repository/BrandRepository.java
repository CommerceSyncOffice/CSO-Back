package commercesyncoffice.org.domain.brand.repository;

import commercesyncoffice.org.domain.admin.model.Admin;
import commercesyncoffice.org.domain.brand.model.Brand;
import commercesyncoffice.org.domain.brand.dto.response.GetBrandListDto;
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

//    @Query("SELECT COUNT(b.id) > 0"
//            + " FROM Brand b"
//            + " JOIN Member m ON m.brand.id = b.id"
//            + " WHERE b.id = :brandId AND m.id = :memberId")
//    boolean existsByIdAndMemberId(Long brandId, Long memberId);

    @Query("SELECT COUNT(b.id) > 0"
            + " FROM Brand b"
            + " JOIN Member m ON m.brand.id = b.id"
            + " WHERE b.id = :brandId AND m.username = :username")
    boolean existsByIdAndMemberUsername(Long brandId, String username);

    @Query("SELECT b"
            + " FROM Brand b"
            + " JOIN Admin a ON b.admin.id = a.id"
            + " WHERE b.id = :brandId")
    Brand findByIdWithAdmin(@Param("brandId") Long brandId);

    @Query("SELECT b"
            + " FROM Brand b"
            + " JOIN MemberGroup mg ON mg.brand.id = b.id"
            + " WHERE mg.id = :memberGroupId")
    Brand findByMemberGroupIdWithAdmin(@Param("memberGroupId") Long memberGroupId);

    @Query("SELECT COUNT(b.id) > 0"
            + " FROM Brand b"
            + " JOIN Member m ON m.brand.id = b.id"
            + " JOIN MemberGroup mg ON mg.brand.id = b.id"
            + " WHERE mg.id = :memberGroupId AND m.username = :username")
    boolean existsByMemberGroupIdAndMemberUsername(@Param("memberGroupId") Long memberGroupId, @Param("username") String username);
}
