package softuni.medident.config;

import org.springframework.context.annotation.Bean;

import javax.validation.Validation;
import javax.validation.Validator;

public class ValidatorConfig {

    @Bean
    public Validator validator(){
        return Validation.buildDefaultValidatorFactory().getValidator();
    }
}
