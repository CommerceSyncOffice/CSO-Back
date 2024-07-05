package commercesyncoffice.org.domain.membergroup.dto.response;

import lombok.Getter;

@Getter
public enum MemberGroupResponseEnum {

    CREATED("Create MemberGroup");

    private final String message;

    MemberGroupResponseEnum(String message) {
        this.message = message;
    }
}
