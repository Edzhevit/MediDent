package softuni.medident.service.services;

import softuni.medident.service.models.ProductServiceModel;

public interface ProductValidatorService {

    boolean isValid(ProductServiceModel serviceModel);
}
