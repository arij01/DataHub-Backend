package esprit.pi.rahma.service;

import esprit.pi.rahma.entities.User;
import esprit.pi.rahma.payload.request.AuthenticationRequest;
import esprit.pi.rahma.payload.request.ChangePasswordRequest;
import esprit.pi.rahma.payload.request.RegisterRequest;
import esprit.pi.rahma.payload.response.AuthenticationResponse;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

public interface AuthenticationService {

    public void supprimeruser(Long id);

    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);


    void setJavaMailSender(JavaMailSender javaMailSender);

    void changePassword(ChangePasswordRequest request, Principal connectedUser);


    //  List<User> getUsersByStatus(int status);

  // void changePassword(ChangePasswordRequest request, Principal connectedUser);

 //   int countADMIN();

    //  void updateUserStatus(Long userId, int newStatus);



   /* User storeFile(MultipartFile file, long id);

    Resource loadFileAsResource(String fileName);*/
}
