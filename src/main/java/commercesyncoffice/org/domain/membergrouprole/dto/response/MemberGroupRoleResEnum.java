package commercesyncoffice.org.domain.membergrouprole.dto.response;

import lombok.Getter;

@Getter
public enum MemberGroupRoleResEnum {
    MEMBER_GROUP_ROLE_CREATED("Member_Group_Role_Created"),;


    private final String message;

    MemberGroupRoleResEnum(String message) {
        this.message = message;
    }
}
