package commercesyncoffice.org.domain.member.dto.request;

public record MemberPasswordChangeDto(
        String oldPassword,
        String newPassword
) {

}
