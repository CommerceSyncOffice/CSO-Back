package commercesyncoffice.org.domain.member.dto;

public record MemberPasswordChangeDto(
        String oldPassword,
        String newPassword
) {

}
