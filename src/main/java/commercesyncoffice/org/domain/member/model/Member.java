package commercesyncoffice.org.domain.member.model;

import commercesyncoffice.org.domain.brand.model.Brand;
import commercesyncoffice.org.domain.member.dto.request.MemberSignUpDto;
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
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Builder
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String username;

    @Column(nullable = false, length = 60)
    private String password;

    @Column(nullable = false, length = 60)
    private String email;

    @Column(columnDefinition = "TEXT")
    private String profileImg;

    @Column(nullable = false)
    private boolean isRandomPassword;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column
    private LocalDateTime modifiedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    public static Member of(MemberSignUpDto memberSignUpDto, Brand brand) {
        UUID uuid = UUID.randomUUID();

        return Member.builder()
                .username(brand.getId() + "_" + memberSignUpDto.username())
                .email(memberSignUpDto.email())
                .password(uuid.toString())
                .isRandomPassword(true)
                .brand(brand)
                .build();
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
        this.isRandomPassword = false;
    }
}
