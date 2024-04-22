package commercesyncoffice.org.domain.brand.repository;

import commercesyncoffice.org.domain.brand.Brand;
import commercesyncoffice.org.domain.brand.dto.GetBrandListDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    @Query("SELECT"
            + " new commercesyncoffice.org.domain.brand.dto.GetBrandListDto(b.name, b.description)"
            + " FROM "
            + " Brand b")
    List<GetBrandListDto> findAllBrandList();
}
