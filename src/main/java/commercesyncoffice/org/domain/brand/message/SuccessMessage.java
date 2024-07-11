package commercesyncoffice.org.domain.brand.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    SUCCESS_CREATE_BRAND(HttpStatus.CREATED, "Successfully created brand"),
    SUCCESS_GET_BRAND_LIST(HttpStatus.OK, "Successfully get brand list");

    private final HttpStatus httpStatus;
    private final String message;
}
