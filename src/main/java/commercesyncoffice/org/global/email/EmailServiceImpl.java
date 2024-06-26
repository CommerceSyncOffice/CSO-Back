package commercesyncoffice.org.global.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    public void sendMail(EmailSignUpDto emailSignUpDto) {
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true, "UTF-8");

            mimeMessageHelper.setTo(emailSignUpDto.to());
            mimeMessageHelper.setSubject(emailSignUpDto.subject());

            Context context = new Context();
            context.setVariable("username", emailSignUpDto.username());
            context.setVariable("password", emailSignUpDto.password());
            context.setVariable("url", emailSignUpDto.url());

            String htmlContent = templateEngine.process("signup-email", context);
            mimeMessageHelper.setText(htmlContent, true);

            javaMailSender.send(mimeMailMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}
