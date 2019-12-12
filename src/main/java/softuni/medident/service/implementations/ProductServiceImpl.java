package softuni.medident.service.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.medident.data.models.Product;
import softuni.medident.data.repositories.ProductRepository;
import softuni.medident.service.models.ProductServiceModel;
import softuni.medident.service.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductServiceModel> getAllProducts() {
        return this.productRepository.findAll()
                .stream().map(p -> this.modelMapper.map(p, ProductServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void createProduct(ProductServiceModel serviceModel) {
        Product product = this.modelMapper.map(serviceModel, Product.class);
        this.productRepository.saveAndFlush(product);
    }

    @Override
    public ProductServiceModel getById(String id) {
        Product product = this.productRepository.getById(id);

        return this.modelMapper.map(product, ProductServiceModel.class);
    }
}
