package softuni.medident.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import softuni.medident.exception.ProductNotFoundException;
import softuni.medident.exception.UserNotFoundException;
import softuni.medident.service.models.ProductServiceModel;
import softuni.medident.service.services.CloudinaryService;
import softuni.medident.service.services.ProductService;
import softuni.medident.web.models.ProductViewDetailsModel;
import softuni.medident.web.models.ProductViewModel;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
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
    public ModelAndView getProducts(ModelAndView modelAndView){

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
    public String createProduct(@ModelAttribute ProductViewModel product) throws IOException, ProductNotFoundException {
        ProductServiceModel serviceModel = this.modelMapper.map(product, ProductServiceModel.class);
        serviceModel.setImageUrl(this.cloudinaryService.upload(product.getImage()));
        this.productService.createProduct(serviceModel);
        return "redirect:/products/all";
    }

    @GetMapping("/details/{id}")
    public ModelAndView getProductDetails(@PathVariable String id, ModelAndView modelAndView) {
        ProductServiceModel serviceModel = productService.getById(id);
        ProductViewDetailsModel viewModel = this.modelMapper.map(serviceModel, ProductViewDetailsModel.class);
        modelAndView.addObject("product", viewModel);
        modelAndView.setViewName(PRODUCT_DETAILS_VIEW_NAME);
        return modelAndView;
    }

    @PostMapping("/buy/{id}")
    public void buyProduct(@PathVariable String id, Principal principal, HttpServletResponse response) throws UserNotFoundException, ProductNotFoundException, IOException {
        String username = principal.getName();
        productService.addToUserById(id, username);
        response.sendRedirect("/products/details/" + id);
    }

    @GetMapping("/my")
    public ModelAndView getMyProducts(Principal principal, ModelAndView modelAndView){
        String username = principal.getName();

        List<ProductViewDetailsModel> myProducts = this.productService.getProductsForUser(username)
                .stream()
                .map(product -> this.modelMapper.map(product, ProductViewDetailsModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("products", myProducts);
        modelAndView.setViewName("products/my-products.html");
        return modelAndView;
    }
}
