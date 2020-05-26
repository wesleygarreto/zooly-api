package br.com.truvainfo.zoolyapi.service;

import br.com.truvainfo.zoolyapi.domain.dto.MessageEmailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailerService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(MessageEmailDTO message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(message.getFrom());
        simpleMailMessage.setTo(message.getReceivers()
                .toArray(new String[message.getReceivers().size()]));
        simpleMailMessage.setSubject(message.getSubject());
        simpleMailMessage.setText(message.getBody());

        javaMailSender.send(simpleMailMessage);
    }

}
