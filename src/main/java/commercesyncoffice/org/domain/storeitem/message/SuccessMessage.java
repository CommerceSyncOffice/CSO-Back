package commercesyncoffice.org.domain.storeitem.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    SUCCESS_CREATE_STORE_ITEM(HttpStatus.CREATED, "Successfully created store item"),
    SUCCESS_SALE_STORE_ITEM(HttpStatus.OK, "Successfully sale store item"),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
