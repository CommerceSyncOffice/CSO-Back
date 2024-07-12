package commercesyncoffice.org.domain.storeitem.repository;

import commercesyncoffice.org.domain.storeitem.model.StoreItem;
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
    Optional<StoreItem> findByIdWithPessimisticLock(@Param("storeId") Long storeItemId);


    @Query("SELECT COUNT(si.id) > 0"
            + " FROM StoreItem si"
            + " JOIN Item i ON si.item.id = i.id"
            + " JOIN Brand b ON i.brand.id = b.id"
            + " JOIN Member m ON m.brand.id = b.id"
            + " WHERE si.id = :storeItemId AND m.username = :username")
    boolean existsMemberByStoreItemIdAndMemberUsername(@Param("storeItemId") Long storeItemId, @Param("username") String username);

    @Query("SELECT COUNT(si.id) > 0"
            + " FROM StoreItem si"
            + " JOIN Item i ON si.item.id = i.id"
            + " JOIN Brand b ON i.brand.id = b.id"
            + " JOIN Admin a ON b.admin.id = a.id"
            + " WHERE si.id = :storeItemId AND a.username = :username")
    boolean existsMemberByStoreItemIdAndAdminUsername(Long storeItemId, String username);
}
