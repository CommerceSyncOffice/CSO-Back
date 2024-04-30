package commercesyncoffice.org.domain.itemserial;

import commercesyncoffice.org.domain.item.Item;
import commercesyncoffice.org.domain.stockreceive.StockReceive;
import commercesyncoffice.org.domain.storeitem.StoreItem;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemSerial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String serial;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @ManyToOne
    @JoinColumn(name = "store_item_id")
    private StoreItem storeItem;

    @ManyToOne
    @JoinColumn(name = "stock_receive_id")
    private StockReceive stockReceive;
}
