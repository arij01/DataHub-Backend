package esprit.pi.rahma.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordRequest {
    private String currentPassword;
    private String newPassword;
    private String confirmationPassword;

    public ChangePasswordRequest() {
        // Default constructor
    }
}
