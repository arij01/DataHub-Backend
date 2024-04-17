package esprit.pi.rahma.config;

import esprit.pi.rahma.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Autowired
    private AuthenticationService authenticationService;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("rahmafarhat9@gmail.com");
        mailSender.setPassword("dcyaydfvntixmjia");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        // Set JavaMailSender in AuthenticationService
        authenticationService.setJavaMailSender(mailSender);



        return mailSender;
    }









    @Bean
    public SimpleMailMessage templateResetPasswordMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Password Reset");
        message.setText("Dear User,\n\nPlease click on the following link to reset your password:\n\n{0}\n\nBest regards,\nYour Application Team");
        return message;
    }
}
