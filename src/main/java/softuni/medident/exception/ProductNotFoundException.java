package softuni.medident.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such product")
public class ProductNotFoundException extends Throwable {

    public ProductNotFoundException(String message){
        super(message);
    }
}
