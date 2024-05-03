package commercesyncoffice.org.domain.storeitem.repository;

import commercesyncoffice.org.domain.storeitem.StoreItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreItemRepository extends JpaRepository<StoreItem, Long> {

    boolean existsByItemIdAndStoreId(Long itemId, Long storeId);
}
