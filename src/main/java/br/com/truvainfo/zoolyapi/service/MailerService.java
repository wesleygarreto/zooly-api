package br.com.truvainfo.zoolyapi.service;

import br.com.truvainfo.zoolyapi.domain.User;
import br.com.truvainfo.zoolyapi.domain.dto.MessageEmailDTO;
import br.com.truvainfo.zoolyapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Collections;

import static br.com.truvainfo.zoolyapi.util.GeneralUtil.getMessage;
import static br.com.truvainfo.zoolyapi.util.GeneralUtil.getTemplateEmail;

@Service
@RequiredArgsConstructor
public class MailerService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserRepository userRepository;

    private void sendEmail(MessageEmailDTO message) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        helper.setFrom(message.getFrom());
        helper.setTo(message.getReceivers()
                .toArray(new String[message.getReceivers().size()]));
        helper.setSubject(message.getSubject());
        helper.setText(message.getBody(), true);

        javaMailSender.send(mimeMessage);
    }

    public boolean sendResetPasswdEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new Exception(getMessage("msg.error.user.id")));
        String url = "https://zooly-web.netlify.app/auth/confirm-password/?u=" + email + "&h=" + user.getHash();
        String emailHtml = getTemplateEmail();
        emailHtml = emailHtml.replace("#{username}", user.getName());
        emailHtml = emailHtml.replace("#{userUrl}", url);

        MessageEmailDTO messageEmailDTO = MessageEmailDTO.builder()
                .subject("Redefinição de Senha - Zooly")
                .receivers(Collections.singletonList(email))
                .from("Zooly Backoffice <zoolybackoffice@gmail.com>")
                .body(emailHtml)
                .build();

        sendEmail(messageEmailDTO);
        return Boolean.TRUE;
    }

}
