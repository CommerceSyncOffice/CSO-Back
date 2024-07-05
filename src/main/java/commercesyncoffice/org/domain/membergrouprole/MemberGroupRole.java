package commercesyncoffice.org.domain.membergrouprole;


import commercesyncoffice.org.domain.membergroup.MemberGroup;
import commercesyncoffice.org.domain.memberrole.MemberRole;
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
public class MemberGroupRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_group_id", nullable = false)
    private MemberGroup memberGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_role_id", nullable = false)
    private MemberRole memberRole;

    public static MemberGroupRole createMemberGroupRole(MemberGroup memberGroup, MemberRole memberRole) {

        return MemberGroupRole.builder()
                              .memberGroup(memberGroup)
                              .memberRole(memberRole)
                              .build();
    }
}
