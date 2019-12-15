package softuni.medident.service.implementations.validations;

import org.springframework.stereotype.Service;
import softuni.medident.service.models.ProductServiceModel;
import softuni.medident.service.services.ProductValidatorService;

@Service
public class ProductValidatorServiceImpl implements ProductValidatorService {

    @Override
    public boolean isValid(ProductServiceModel serviceModel) {
        return serviceModel != null;
    }
}
