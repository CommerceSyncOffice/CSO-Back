package commercesyncoffice.org.domain.store.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

    NOT_FOUND_STORE(HttpStatus.NOT_FOUND, "해당 스토어는 존재하지 않습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
