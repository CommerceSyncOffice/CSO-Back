package commercesyncoffice.org.domain.membergroupmember.model;

import commercesyncoffice.org.domain.member.model.Member;
import commercesyncoffice.org.domain.membergroup.model.MemberGroup;
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
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberGroupMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_group_id", nullable = false)
    private MemberGroup memberGroup;

    public static MemberGroupMember of(Member member, MemberGroup memberGroup) {

        return MemberGroupMember.builder()
                                .member(member)
                                .memberGroup(memberGroup)
                                .build();
    }
}
