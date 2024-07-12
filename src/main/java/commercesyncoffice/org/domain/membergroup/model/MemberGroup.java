package commercesyncoffice.org.domain.membergroup.model;

import commercesyncoffice.org.domain.brand.model.Brand;
import commercesyncoffice.org.domain.membergroup.dto.request.MemberGroupCreateDto;
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
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    public static MemberGroup of(MemberGroupCreateDto memberGroupCreateDto, Brand brand) {

        return MemberGroup.builder()
                          .name(memberGroupCreateDto.name())
                          .description(memberGroupCreateDto.description())
                          .brand(brand)
                          .build();
    }
}
