package esprit.pi.rahma.controller;

import esprit.pi.rahma.payload.request.ChangePasswordRequest;
import esprit.pi.rahma.payload.request.RefreshTokenRequest;
import esprit.pi.rahma.repository.UserRepository;
import esprit.pi.rahma.entities.User;
import esprit.pi.rahma.exception.ResourceNotFoundException;
import esprit.pi.rahma.payload.request.AuthenticationRequest;
import esprit.pi.rahma.payload.request.RegisterRequest;
import esprit.pi.rahma.payload.response.AuthenticationResponse;
import esprit.pi.rahma.payload.response.RefreshTokenResponse;
import esprit.pi.rahma.service.AuthenticationService;
import esprit.pi.rahma.service.JwtService;
import esprit.pi.rahma.service.RefreshTokenService;
import esprit.pi.rahma.service.ResetPasswordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Tag(name = "Authentication", description = "The Authentication API. Contains operations like login, logout, refresh-token etc.")
@RestController
@RequestMapping("/api/v1/auth")
@SecurityRequirements() /*
This API won't have any security requirements. Therefore, we need to override the default security requirement configuration
with @SecurityRequirements()
*/
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private ResetPasswordService resetPasswordService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequest request) {
        AuthenticationResponse authenticationResponse = authenticationService.register(request);
        ResponseCookie jwtCookie = jwtService.generateJwtCookie(authenticationResponse.getAccessToken());
        ResponseCookie refreshTokenCookie = refreshTokenService.generateRefreshTokenCookie(authenticationResponse.getRefreshToken());
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                .body(authenticationResponse);

    }

    @GetMapping("/Users")
    @ResponseStatus(HttpStatus.CREATED)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")}
                    )
            }
    )
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(request);
        ResponseCookie jwtCookie = jwtService.generateJwtCookie(authenticationResponse.getAccessToken());
        ResponseCookie refreshTokenCookie = refreshTokenService.generateRefreshTokenCookie(authenticationResponse.getRefreshToken());
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                .body(authenticationResponse);
    }

    @PostMapping("/refresh-token")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RefreshTokenResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(refreshTokenService.generateNewToken(request));
    }

    @PutMapping("/Users/{id}")
    @ResponseStatus(HttpStatus.CREATED)

    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        // Tente de trouver l'user par ID ; lance une exception s'il n'est pas trouvé
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));

        // Met à jour les détails de l'user
        user.setFirstname(userDetails.getFirstname()); // Utilisation du bon nom de champ
        user.setLastname(userDetails.getLastname()); // Utilisation du bon nom de champ
        user.setEmail(userDetails.getEmail()); // Utilisation du bon nom de champ

        // Enregistre l'user mis à jour dans la base de données
        User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping("/refresh-token-cookie")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> refreshTokenCookie(HttpServletRequest request) {
        String refreshToken = refreshTokenService.getRefreshTokenFromCookies(request);
        RefreshTokenResponse refreshTokenResponse = refreshTokenService
                .generateNewToken(new RefreshTokenRequest(refreshToken));
        ResponseCookie NewJwtCookie = jwtService.generateJwtCookie(refreshTokenResponse.getAccessToken());
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, NewJwtCookie.toString())
                .build();
    }

    @GetMapping("/info")
    @ResponseStatus(HttpStatus.CREATED)
    public Authentication getAuthentication(@RequestBody AuthenticationRequest request) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    }

    // get User by id rest api
    @GetMapping("/Users/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        // Tente de trouver l'user par ID ; lance une exception s'il n'est pas trouvé
        User user = userRepository.findById(id)

                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        String refreshToken = refreshTokenService.getRefreshTokenFromCookies(request);
        if (refreshToken != null) {
            refreshTokenService.deleteByToken(refreshToken);
        }
        ResponseCookie jwtCookie = jwtService.getCleanJwtCookie();
        ResponseCookie refreshTokenCookie = refreshTokenService.getCleanRefreshTokenCookie();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                .build();

    }


    @DeleteMapping("/supprimerUser/{id}")
    public void deleteUser(@PathVariable("id") long id) {
        authenticationService.supprimeruser(id);
    }


    @GetMapping("/statistics")
    public Map<String, Long> getUserStatisticsByRole() {
        List<User> users = userRepository.findAll();
        Map<String, Long> roleStatistics = new HashMap<>();

        for (User user : users) {
            String roleName = user.getRole().toString(); // Assurez-vous que getRole() renvoie une enum Role
            roleStatistics.put(roleName, roleStatistics.getOrDefault(roleName, 0L) + 1);
        }

        return roleStatistics;
    }


  @CrossOrigin(origins = "http://localhost:4200") // Allow requests from Angular app

  @PostMapping("/forgot-password")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<?> forgotPassword(@RequestParam String email) {
      resetPasswordService.sendPasswordResetEmail(email);
      return ResponseEntity.ok().build();
  }

    @PatchMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request, Principal connectedUser) {
        authenticationService.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

  /*  @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String email) {
        try {
            resetPasswordService.sendPasswordResetEmail(email);
            return ResponseEntity.ok("Password reset link sent to your email.");
        } catch (MessagingException | jakarta.mail.MessagingException e) {
            // Handle the exception (e.g., log error, return error response)
            return ResponseEntity.status(500).body("Failed to send password reset email.");
        }
    }*/




   /* @PatchMapping("/change-password")




   @GetMapping("/adminstat")
    public int countADMIN() {
        return authenticationService.countADMIN();
    }*/
}

   /* @PatchMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request, Principal connectedUser) {
        authenticationService.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }
}*/




 /*   @GetMapping("/users/status/{status}")
    public ResponseEntity<List<User>> getUsersByStatus(@PathVariable int status) {
        List<User> users = authenticationService.getUsersByStatus(status);
        return ResponseEntity.ok(users);
    }*/




/*    public ResponseEntity<String> updateUserStatus(
            @PathVariable Long userId,
            @PathVariable int newStatus
    ) {
        authenticationService.updateUserStatus(userId, newStatus);
        return ResponseEntity.ok("Statut de l'utilisateur mis à jour avec succès.");
    }
}*/




  /*  @GetMapping("/photo/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileName") String fileName) {
        Resource resource = authenticationService.loadFileAsResource(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


    //upload image
    @PostMapping("/photo/upload/{id}")
    public User handleFileUpload(@RequestParam("photo") MultipartFile file,
                                    @PathVariable("id") long id) {

        return authenticationService.storeFile(file,id);
    }
    */
