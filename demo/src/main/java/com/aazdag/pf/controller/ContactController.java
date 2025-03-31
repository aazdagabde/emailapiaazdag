package com.aazdag.pf.controller;


import com.aazdag.pf.dto.ContactForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "https://aazdagabdepf.netlify.app")
public class ContactController {

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/send")
    public String sendEmail(@RequestBody ContactForm form) {

        SimpleMailMessage message = new SimpleMailMessage();
        
        message.setFrom(form.getEmail());
        message.setTo("aazdag.abdellah@gmail.com"); // ton email personnel
        message.setSubject(form.getSubject());
        message.setText(
            "Nom : " + form.getName() + "\n" +
            "E-mail : " + form.getEmail() + "\n\n" +
            form.getMessage()
        );

        mailSender.send(message);

        return "Email envoyé avec succès";
    }
}