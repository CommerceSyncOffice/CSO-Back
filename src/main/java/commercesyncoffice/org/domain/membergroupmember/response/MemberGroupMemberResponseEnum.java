package commercesyncoffice.org.domain.membergroupmember.response;

import lombok.Getter;

@Getter
public enum MemberGroupMemberResponseEnum {

    RESISTER_MEMBER("멤버 그룹 등록이 완료되었어용~~");

    private final String message;

    MemberGroupMemberResponseEnum(String message) {
        this.message = message;
    }
}
