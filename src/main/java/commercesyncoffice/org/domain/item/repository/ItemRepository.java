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

    @Query("SELECT COUNT(i.id) > 0"
            + " FROM Item i"
            + " JOIN ItemSerial is ON i.id = is.item.id"
            + " WHERE i.id = :itemId AND is.serial IS NOT NULL"
    )
    boolean isHavingSerial(@Param("itemId") Long itemId);

    @Query("SELECT COUNT(i.id) > 0"
            + " FROM Item i"
            + " JOIN i.category c"
            + " JOIN c.brand b"
            + " WHERE i.barcode = :barcode AND b.id = :brandId"
    )
    boolean checkSameBarcodeInBrand(@Param("barcode") String barcode,
            @Param("brandId") Long brandId);
}
