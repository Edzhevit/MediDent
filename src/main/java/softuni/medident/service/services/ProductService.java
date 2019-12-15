package softuni.medident.service.services;

import softuni.medident.exception.ProductNotFoundException;
import softuni.medident.exception.UserNotFoundException;
import softuni.medident.service.models.ProductServiceModel;

import java.util.List;

public interface ProductService {

    List<ProductServiceModel> getAllProducts();

    void createProduct(ProductServiceModel serviceModel) throws ProductNotFoundException;

    ProductServiceModel getById(String id);

    void addToUserById(String id, String username) throws UserNotFoundException, ProductNotFoundException;

    List<ProductServiceModel> getProductsForUser(String username);
}
