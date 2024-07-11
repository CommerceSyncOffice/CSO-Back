package commercesyncoffice.org.domain.category.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

    NOT_FOUND_CATEGORY(HttpStatus.NOT_FOUND, "해당 카테고리는 존재하지 않습니다");

    private final HttpStatus httpStatus;
    private final String message;
}
