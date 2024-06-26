package commercesyncoffice.org.global.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    public void sendMail(EmailDto emailDto) {
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, false, "UTF-8");

            mimeMessageHelper.setTo(emailDto.to());
            mimeMailMessage.setSubject(emailDto.subject());
            mimeMailMessage.setText(emailDto.text());

            javaMailSender.send(mimeMailMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}
