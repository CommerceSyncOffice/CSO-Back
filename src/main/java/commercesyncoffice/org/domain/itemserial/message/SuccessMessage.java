package commercesyncoffice.org.domain.itemserial.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    SUCCESS_CREATE_ITEM_SERIAL(HttpStatus.CREATED, "Successfully created itemSerial");

    private final HttpStatus httpStatus;
    private final String message;
}
