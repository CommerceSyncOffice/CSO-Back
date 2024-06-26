package commercesyncoffice.org.global.email;

public record EmailSignUpDto(
        String to,
        String subject,
        String username,
        String password,
        String url
){

}
