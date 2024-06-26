package commercesyncoffice.org.domain.admin;

import commercesyncoffice.org.domain.admin.dto.AdminSignUpDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 20, nullable = false)
    private String username;

    @Column(length = 60, nullable = false)
    private String password;

    @Column(length = 60, unique = true, nullable = false)
    private String email;

    @Column(columnDefinition = "TEXT")
    private String profileImg;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column
    private LocalDateTime modifiedAt;

    public static Admin signup(AdminSignUpDto adminSignUpDto, String encodedPassword) {

        return Admin.builder()
                    .username(adminSignUpDto.username())
                    .password(encodedPassword)
                    .email(adminSignUpDto.email())
                    .profileImg(adminSignUpDto.profileImg())
                    .build();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
