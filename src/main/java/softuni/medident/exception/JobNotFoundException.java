package softuni.medident.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such job application")
public class JobNotFoundException extends Exception {

    public JobNotFoundException(String message){
        super(message);
    }

}
