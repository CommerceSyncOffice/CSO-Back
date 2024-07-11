package commercesyncoffice.org.domain.stockreceive.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    SUCCESS_CREATE_STOCK_RECEIVE(HttpStatus.CREATED, "Successfully created stock receive"),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
