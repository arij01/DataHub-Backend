package esprit.pi.rahma.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


// Annotation indiquant que la classe représente une exception de statut NOT_FOUND
@ResponseStatus(value = HttpStatus.NOT_FOUND)
// Étend la classe RuntimeException, ce qui en fait une exception non vérifiée

public class ResourceNotFoundException extends RuntimeException{
	// Numéro de version pour la sérialisation

	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
