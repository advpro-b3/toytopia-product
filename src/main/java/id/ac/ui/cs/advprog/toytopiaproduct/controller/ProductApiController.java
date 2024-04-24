package id.ac.ui.cs.advprog.toytopiaproduct.controller;

import id.ac.ui.cs.advprog.toytopiaproduct.model.Product;
import id.ac.ui.cs.advprog.toytopiaproduct.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-service")
public class ProductApiController {
    @Autowired
    private ProductServiceImpl productService;

    @GetMapping("/all-products")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/product/{productId}")
    public Product getProductById(@PathVariable String productId) {
        return productService.findById(productId);
    }
}