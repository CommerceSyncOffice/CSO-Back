package commercesyncoffice.org.domain.membergroup.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

    NOT_FOUND_MEMBER_GROUP(HttpStatus.NOT_FOUND, "해당 그룹은 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
