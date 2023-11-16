package com.example.employee.service;

import com.example.employee.domain.EmailModel;
import com.example.employee.domain.TypeEmailEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public SimpleMailMessage emailBuilder(EmailModel email){

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email.getRecipient());
        mailMessage.setFrom(sender);

        if(email.getType() == TypeEmailEnum.ADMISSION){
            mailMessage.setText("Seja bem vindo a empresa Edson Desenvolvimento de Sistemas. É um prazer ter você no time!");
            mailMessage.setSubject("Boas vindas!");
        }else if(email.getType() == TypeEmailEnum.RESIGNATION){
            mailMessage.setText("Foi um prazer ter você no time da Edson Desenvolvimento de Sistemas. Somos gratos a todos os seus serviçoes prestados e desejamos sorte nas próximas etapas de sua gloriosa carreia de dev!");
            mailMessage.setSubject("Informativo de demissão!");
        }
        //TODO implement demonstrative email
        return mailMessage;
    }

    public void sendEmail(SimpleMailMessage mailMessage){
        javaMailSender.send(mailMessage);
    }

}
