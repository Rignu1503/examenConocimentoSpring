package com.riwi.examen_Rigo.infrastructure.adtract_services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class EmailHelper {
    
        private final JavaMailSender mailSender;


   public void sendMail(String destinity, String name, LocalDateTime date){

        //Indica que es html
        MimeMessage message = mailSender.createMimeMessage();

        //Covertimos la fecha con este formato
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateAppointment = date.format(formatter);

        //retornamos los valores
        String htmlContent = this.readHTMLTemplate(name, dateAppointment);

        try {
            //remitente
            message.setFrom(new InternetAddress("rigoberto.miranda1503@gmail.com"));
            //Asunto
            message.setSubject("Correo del filtro");

            //receptores 
            message.setRecipients(MimeMessage.RecipientType.TO,destinity);
            //Contenido
            message.setContent(htmlContent,MediaType.TEXT_HTML_VALUE);

            //Enviar mensaje
            mailSender.send(message);
            System.out.println("Email enviado");

        } catch (Exception e) {
            System.out.println("ERROR no se pudo enviar el email " + e.getMessage());

        }
    }



    private String readHTMLTemplate(String name, String  date){

        //donde se encuentra el template
        final Path path = Paths.get("src/main/resources/emails/email.html");

        try (var lines = Files.lines(path)){
            //Une la lineas html que estan separa para poner todo en una linea
            var html = lines.collect(Collectors.joining());
            //reemplaza el nombre del cliente en el template
            return html.replace("{nameClient}", name).replace("{fecha}", date);


        } catch (IOException e) {
            
            System.out.println("No se pudo leer el html");
            throw new RuntimeException();
           
        }

    }
}
