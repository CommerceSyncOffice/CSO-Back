package commercesyncoffice.org.domain.stockrequest.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

    ;

    private final HttpStatus httpStatus;
    private final String message;
}
