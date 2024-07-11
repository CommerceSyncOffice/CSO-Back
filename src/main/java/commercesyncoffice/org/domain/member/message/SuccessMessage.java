package commercesyncoffice.org.domain.member.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    SUCCESS_SIGN_UP(HttpStatus.CREATED, "Successfully signed up"),
    SUCCESS_LOGIN(HttpStatus.OK, "Successfully logged in"),
    SUCCESS_CHANGE_PASSWORD(HttpStatus.OK, "Successfully changed password"),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
