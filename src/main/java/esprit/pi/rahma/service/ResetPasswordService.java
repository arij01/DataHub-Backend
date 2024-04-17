package esprit.pi.rahma.service;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.UUID;

@Service
public class ResetPasswordService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendPasswordResetEmail(String email) {
        // Générer et enregistrer le token de réinitialisation dans la base de données

        // Envoyer l'email de réinitialisation avec le lien contenant le token
        String resetLink = "http://votre-application/reset-password?token=votre-jeton-de-reinitialisation";
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Réinitialisation de mot de passe");
        mailMessage.setText("Cliquez sur ce lien pour réinitialiser votre mot de passe : " + resetLink);
        javaMailSender.send(mailMessage);
    }
}
