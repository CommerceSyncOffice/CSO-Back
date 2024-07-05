package commercesyncoffice.org.domain.memberrole;

import commercesyncoffice.org.domain.memberrole.repository.MemberRoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberRoleInitializer {

    private final MemberRoleRepository memberRoleRepository;
    private final JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        removeCheckConstraint();
        addCheckConstraint();
        initializeRoles();
    }

    private void removeCheckConstraint() {
        jdbcTemplate.execute("ALTER TABLE member_role DROP CONSTRAINT IF EXISTS member_role_name_check");
    }

    private void addCheckConstraint() {
        String[] enumValue = new String[MemberRoleEnum.values().length];

        for (int i = 0; i < enumValue.length; i++) {
            enumValue[i] = "'" + MemberRoleEnum.values()[i].name() + "'";
        }
        String checkConstraint = "CHECK (name IN(" + String.join(", ", enumValue) + "))";

        jdbcTemplate.execute("ALTER TABLE member_role ADD CONSTRAINT member_role_name_check " + checkConstraint);
    }

    private void initializeRoles() {
        for (MemberRoleEnum memberRoleEnum : MemberRoleEnum.values()) {
            memberRoleRepository.findByName(memberRoleEnum).orElseGet(
                    () -> {
                        MemberRole role = MemberRole.builder()
                                .name(memberRoleEnum)
                                .description(memberRoleEnum.getDescription())
                                .build();
                        return memberRoleRepository.save(role);
                    }
            );
        }
    }
}
