package id.ac.ui.cs.advprog.toytopiaproduct.controller;

import id.ac.ui.cs.advprog.toytopiaproduct.model.Product;
import id.ac.ui.cs.advprog.toytopiaproduct.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @PostMapping("/create-product")
    public Product createProduct(@RequestBody HashMap<String, String> productAttributes) {

        Product product = new Product.ProductBuilder(productAttributes.get("name"))
                .setDescription(productAttributes.get("description"))
                .setPrice(Integer.parseInt(productAttributes.get("price")))
                .setStock(Integer.parseInt(productAttributes.get("stock")))
                .setDiscount(Integer.parseInt(productAttributes.get("discount")))
                .setAvailability(productAttributes.get("availability"))
                .build();
        return productService.create(product);
    }
}