package softuni.medident.service.validation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import softuni.medident.constants.Constants;
import softuni.medident.service.base.ServiceTestBase;
import softuni.medident.service.models.JobApplicationServiceModel;
import softuni.medident.service.services.JobApplicationValidatorService;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JobApplicationValidatorServiceTest extends ServiceTestBase {

    @Autowired
    JobApplicationValidatorService service;

    @Test
    void isValid_whenAllValid_ShouldReturnTrue(){
        JobApplicationServiceModel serviceModel = new JobApplicationServiceModel(Constants.DEFAULT_ID, Constants.DEFAULT_NAME,
                Constants.DEFAULT_LOCATION, Constants.DEFAULT_DESCRIPTION, Constants.DEFAULT_SALARY);
        boolean isValid = service.isValid(serviceModel);
        assertTrue(isValid);
    }

    @Test
    void isValid_whenIsNull_ShouldReturnFalse(){
        JobApplicationServiceModel serviceModel = null;
        boolean isValid = service.isValid(serviceModel);
        assertFalse(isValid);
    }
}
