package commercesyncoffice.org.domain.storeitem.repository;

import commercesyncoffice.org.domain.storeitem.StoreItem;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreItemRepository extends JpaRepository<StoreItem, Long> {

    boolean existsByItemIdAndStoreId(Long itemId, Long storeId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT st"
            + " FROM StoreItem st"
            + " WHERE st.id = :storeId")
    Optional<StoreItem> findByIdWithPessimisticLock(@Param("storeId") Long storeId);
}
