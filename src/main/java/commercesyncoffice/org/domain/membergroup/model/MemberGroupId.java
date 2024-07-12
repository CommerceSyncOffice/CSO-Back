package commercesyncoffice.org.domain.membergroup.model;

public record MemberGroupId(Long id) {
    public static MemberGroupId from(Long id) {
        return new MemberGroupId(id);
    }
}
