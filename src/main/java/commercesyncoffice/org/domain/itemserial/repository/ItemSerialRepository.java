package commercesyncoffice.org.domain.itemserial.repository;

import commercesyncoffice.org.domain.itemserial.model.ItemSerial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemSerialRepository extends JpaRepository<ItemSerial, Long> {

    @Query("SELECT COUNT(i.id) > 0"
            + " FROM ItemSerial is"
            + " JOIN is.item i"
            + " WHERE is.serial = :serial AND i.id = :itemId"
    )
    boolean checkSameSerialInItem(@Param("serial") String serial,
            @Param("itemId") Long itemId);
}
