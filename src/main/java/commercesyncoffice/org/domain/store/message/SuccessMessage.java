package commercesyncoffice.org.domain.store.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    SUCCESS_CREATE_STORE(HttpStatus.CREATED, "Successfully created store"),
    SUCCESS_GET_STORE_LIST(HttpStatus.OK, "Successfully retrieved store list"),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
