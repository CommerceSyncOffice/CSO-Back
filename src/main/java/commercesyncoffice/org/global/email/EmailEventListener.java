package commercesyncoffice.org.global.email;


import commercesyncoffice.org.domain.member.event.MemberSignUpEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class EmailEventListener {

    private final EmailServiceImpl emailServiceImpl;

    @Async
    @TransactionalEventListener
    public void handleAfterCommitMemberSignUp(MemberSignUpEvent memberSignUpEvent) {
        String subject = "CSO - 가입 아이디 및 임시비밀번호";

        EmailSignUpDto emailSignUpDto = new EmailSignUpDto(memberSignUpEvent.email(), subject,
                memberSignUpEvent.username(), memberSignUpEvent.password(),
                memberSignUpEvent.address());
        emailServiceImpl.sendMail(emailSignUpDto);
    }
}
