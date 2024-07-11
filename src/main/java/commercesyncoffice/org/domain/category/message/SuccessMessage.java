package commercesyncoffice.org.domain.category.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    SUCCESS_CREATE_CATEGORY(HttpStatus.CREATED, "Successfully created category"),
    SUCCESS_GET_CATEGORY_LIST(HttpStatus.OK, "Successfully retrieved category list");

    private final HttpStatus httpStatus;
    private final String message;
}
