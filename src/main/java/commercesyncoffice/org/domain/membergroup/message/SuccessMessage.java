package commercesyncoffice.org.domain.membergroup.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    SUCCESS_CREATE_MEMBER_GROUP(HttpStatus.CREATED, "Successfully created member group"),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
