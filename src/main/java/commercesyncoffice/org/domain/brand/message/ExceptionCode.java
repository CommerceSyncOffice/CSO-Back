package commercesyncoffice.org.domain.brand.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

    YOUR_NOT_ADMIN_THIS_BRAND(HttpStatus.BAD_REQUEST, "이 브랜드를 수정할 권한이 없습니다."),
    YOUR_NOT_MEMBER_THIS_BRAND(HttpStatus.BAD_REQUEST, "이 브랜드를 수정할 권한이 없습니다."),
    NOT_FOUND_BRAND(HttpStatus.NOT_FOUND, "해당 브랜드는 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
