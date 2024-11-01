package com.is.servidor_barrio.business.logic.service;


import com.is.servidor_barrio.business.domain.entity.Habitante;
import com.is.servidor_barrio.business.domain.entity.Visitante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EnvioCorreoService {

    private JavaMailSender mailSender;

    public EnvioCorreoService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Autowired
    public void EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    public void sendEmail(Habitante habitante, Visitante visitante) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(habitante.getCorreo());
        String subject = "Aviso de entrada de visitante al barrio";
        message.setSubject(subject);
        String text = "Desde la seguridad del barrio queremos avisarle que ingresó hacia su inmueble: "
                + visitante.getNombre() + ", " + visitante.getApellido() +
                " (" + visitante.getTipoVisita() + ")";
        message.setText(text);
        message.setFrom("interisys2@gmail.com");
        mailSender.send(message);
    }
}
