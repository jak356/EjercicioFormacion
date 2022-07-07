package com.example.BackEmpresa.utilidades.correoenviar;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CorreoSenderService {
    @Autowired
    JavaMailSender mailSender;

    public void sendMail(String destino, String sujeto, String texto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("exercisecloudfinal@outlook.es");
        message.setTo(destino);
        message.setSubject(sujeto);
        message.setText(texto);

        mailSender.send(message);
        log.info("Email enviado a: " + destino + " correctamente");

    }

}
