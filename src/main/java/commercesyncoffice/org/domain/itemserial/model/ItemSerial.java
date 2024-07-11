package commercesyncoffice.org.domain.itemserial.model;

import commercesyncoffice.org.domain.item.model.Item;
import commercesyncoffice.org.domain.itemserial.dto.request.ItemSerialCreateDto;
import commercesyncoffice.org.domain.stockreceive.StockReceive;
import commercesyncoffice.org.domain.storeitem.StoreItem;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemSerial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String serial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_item_id")
    private StoreItem storeItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_receive_id")
    private StockReceive stockReceive;

    public static ItemSerial createItemSerial(
            ItemSerialCreateDto itemSerialCreateDto,
            Item item
    ) {

        return ItemSerial.builder()
                         .serial(itemSerialCreateDto.serial())
                         .item(item)
                         .build();
    }
}
