package commercesyncoffice.org.domain.member.event;

public record MemberSignUpEvent(
        String email,
        String username,
        String password
) {

}
