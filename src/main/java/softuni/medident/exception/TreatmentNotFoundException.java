package softuni.medident.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such treatment")
public class TreatmentNotFoundException extends Throwable{

    public TreatmentNotFoundException(String message){
        super(message);
    }
}
