package softuni.medident.service.services;

import softuni.medident.service.models.ProductServiceModel;

import java.util.List;

public interface ProductService {

    List<ProductServiceModel> getAllProducts();

    void createProduct(ProductServiceModel serviceModel);

    ProductServiceModel getById(String id);
}
