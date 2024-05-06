package commercesyncoffice.org.domain.stockreceive;

import commercesyncoffice.org.domain.item.Item;
import commercesyncoffice.org.domain.stockreceive.dto.StockReceiveCreateDto;
import commercesyncoffice.org.domain.store.Store;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Builder
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockReceive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer receiveStockCnt;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    public static StockReceive createStockReceive(
            StockReceiveCreateDto stockReceiveCreateDto,
            Item item,
            Store store
    ) {

        return StockReceive.builder()
                           .receiveStockCnt(stockReceiveCreateDto.receiveStockCnt())
                           .item(item)
                           .store(store)
                           .build();
    }
}
