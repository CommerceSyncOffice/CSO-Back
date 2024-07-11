package commercesyncoffice.org.domain.stockrequest.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    SUCCESS_CREATE_STOCK_REQUEST(HttpStatus.CREATED, "Successfully created stock request"),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
