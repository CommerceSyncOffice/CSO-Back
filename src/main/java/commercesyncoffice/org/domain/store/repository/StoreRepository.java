package commercesyncoffice.org.domain.store.repository;

import commercesyncoffice.org.domain.store.model.Store;
import commercesyncoffice.org.domain.store.dto.response.StoreListDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query("SELECT"
            + " new commercesyncoffice.org.domain.store.dto.response.StoreListDto(s.name, s.address)"
            + " FROM Store s"
            + " WHERE s.brand.id = :brandId"
    )
    List<StoreListDto> findStoreListByBrand(@Param("brandId") Long brandId);
}
