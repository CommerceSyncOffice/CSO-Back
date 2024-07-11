package commercesyncoffice.org.domain.membergroupmember.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    SUCCESS_RESISTER_MEMBER(HttpStatus.CREATED, "Successfully resister member"),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
