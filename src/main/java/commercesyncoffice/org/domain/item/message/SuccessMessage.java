package commercesyncoffice.org.domain.item.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    SUCCESS_CREATE_ITEM(HttpStatus.CREATED, "Successfully created item"),
    SUCCESS_CHANGE_ITEM_IS_SERIAL(HttpStatus.OK, "Successfully changed serial item"),
    SUCCESS_CHANGE_ITEM_CATEGORY(HttpStatus.OK, "Successfully changed Item Category"),
    SUCCESS_GET_ITEM(HttpStatus.OK, "Successfully get item");

    private final HttpStatus httpStatus;
    private final String message;
}
