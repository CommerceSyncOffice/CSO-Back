package commercesyncoffice.org.domain.item;

import commercesyncoffice.org.domain.brand.Brand;
import commercesyncoffice.org.domain.category.Category;
import commercesyncoffice.org.domain.item.dto.ItemCreateDto;
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
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private Integer originPrice;

    @Column(nullable = false)
    private Integer price;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String img;

    @Column(nullable = false, length = 20)
    private String barcode;

    @Column(nullable = false)
    private Boolean isSerial;

    @Column(nullable = false)
    private Boolean isDeleted;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column
    private LocalDateTime modifiedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public Long getId() {

        return id;
    }

    public Boolean getIsSerial() {

        return isSerial;
    }

    public Long getBrandId() {

        return brand.getId();
    }

    public static Item createItem(ItemCreateDto itemCreateDto, Category category, Brand brand) {

        return Item.builder()
                .name(itemCreateDto.name())
                .description(itemCreateDto.description())
                .price(itemCreateDto.price())
                .barcode(itemCreateDto.barcode())
                .img(itemCreateDto.img())
                .isDeleted(false)
                .originPrice(itemCreateDto.originPrice())
                .isSerial(itemCreateDto.isSerial())
                .category(category)
                .brand(brand)
                .build();
    }

    public void changeIsSerial() {

        isSerial = !isSerial;
    }

    public void changeCategory(Category category) {

        this.category = category;
    }
}
