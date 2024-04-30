package commercesyncoffice.org.domain.item.repository;

import commercesyncoffice.org.domain.item.Item;
import commercesyncoffice.org.domain.item.dto.ItemDetailDto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT"
            + " new commercesyncoffice.org.domain.item.dto.ItemDetailDto(i.name, i.description, c.name, i.barcode, i.originPrice, i.price, i.isSerial, i.img)"
            + " FROM Item i"
            + " LEFT JOIN i.category c"
            + " WHERE i.id = :itemId"
    )
    Optional<ItemDetailDto> findByIdCustom(@Param("itemId") Long itemId);
}
