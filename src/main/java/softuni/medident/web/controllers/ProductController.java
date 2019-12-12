package softuni.medident.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import softuni.medident.service.models.ProductServiceModel;
import softuni.medident.service.services.CloudinaryService;
import softuni.medident.service.services.ProductService;
import softuni.medident.web.models.ProductViewDetailsModel;
import softuni.medident.web.models.ProductViewModel;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
public class ProductController {

    public final static String PRODUCT_DETAILS_VIEW_NAME = "products/product-details.html";

    private final ProductService productService;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public ProductController(ProductService productService, ModelMapper modelMapper, CloudinaryService cloudinaryService) {
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping("/all")
    public ModelAndView getJobs(ModelAndView modelAndView){

        List<ProductViewModel> products = this.productService.getAllProducts()
                .stream()
                .map(p -> this.modelMapper.map(p, ProductViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("products", products);
        modelAndView.setViewName("products/all-products.html");
        return modelAndView;
    }

    @GetMapping("/create")
    public String getCreateProductForm(){
        return "products/create-product.html";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute ProductViewModel product) throws IOException {
        ProductServiceModel serviceModel = this.modelMapper.map(product, ProductServiceModel.class);
        serviceModel.setImageUrl(this.cloudinaryService.upload(product.getImage()));
        this.productService.createProduct(serviceModel);
        return "redirect:/products/details/" + product.getModel();
    }

    @GetMapping("/details/{id}")
    public ModelAndView getProductDetails(@PathVariable String id, ModelAndView modelAndView) {
        ProductServiceModel serviceModel = productService.getById(id);
        ProductViewDetailsModel viewModel = this.modelMapper.map(serviceModel, ProductViewDetailsModel.class);
        modelAndView.addObject("product", viewModel);
        modelAndView.setViewName(PRODUCT_DETAILS_VIEW_NAME);
        return modelAndView;
    }
}
