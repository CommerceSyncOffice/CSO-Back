package commercesyncoffice.org.domain.storeitem;

import commercesyncoffice.org.domain.item.model.Item;
import commercesyncoffice.org.domain.store.model.Store;
import commercesyncoffice.org.domain.storeitem.dto.request.StoreItemCreateDto;
import commercesyncoffice.org.domain.storeitem.dto.request.StoreItemSaleDto;
import commercesyncoffice.org.domain.storeitem.exception.StoreItemException;
import commercesyncoffice.org.domain.storeitem.message.ExceptionCode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
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
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Builder
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private Integer saleCnt;

    @Column
    private Integer recommend_stock;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column
    private LocalDateTime modifiedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    public static StoreItem of(
            Store store,
            Item item,
            StoreItemCreateDto storeItemCreateDto
    ) {

        return StoreItem.builder()
                        .store(store)
                        .item(item)
                        .stock(storeItemCreateDto.stock())
                        .saleCnt(storeItemCreateDto.saleStock())
                        .recommend_stock(storeItemCreateDto.recommendStock() == 0 ? null : storeItemCreateDto.recommendStock())
                        .build();
    }

    public void saleStock(StoreItemSaleDto storeItemSaleDto) {

        if (this.stock - storeItemSaleDto.saleCnt() < 0) {
            throw new StoreItemException(ExceptionCode.CAN_NOT_SALE_MORE);
        } else {
            this.stock -= storeItemSaleDto.saleCnt();
        }

        this.saleCnt += storeItemSaleDto.saleCnt();
    }

    public Long getId() {

        return this.id;
    }

    public Integer getStock() {

        return this.stock;
    }

    public Integer getSaleCnt() {

        return this.saleCnt;
    }
}
