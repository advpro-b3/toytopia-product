package id.ac.ui.cs.advprog.toytopiaproduct.controller;

import id.ac.ui.cs.advprog.toytopiaproduct.model.Product;
import id.ac.ui.cs.advprog.toytopiaproduct.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/product-service")
public class ProductApiController {
    @Autowired
    private ProductServiceImpl productService;

    @GetMapping("/all-products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.findAll();
        if (products.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(products);
        }
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable String productId) {
        Product product = productService.findById(productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(product);
        }
    }

    @PostMapping("/create-product")
    public ResponseEntity<Product> createProduct(@RequestBody HashMap<String, String> productAttributes) {
        Product product = new Product.ProductBuilder(productAttributes.get("name"))
                .setDescription(productAttributes.get("description"))
                .setPrice(Integer.parseInt(productAttributes.get("price")))
                .setStock(Integer.parseInt(productAttributes.get("stock")))
                .setDiscount(Integer.parseInt(productAttributes.get("discount")))
                .setAvailability(productAttributes.get("availability"))
                .build();
        if (product.isValid()){
            return ResponseEntity.ok(productService.create(product));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update-product/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable String productId, @RequestBody HashMap<String, String> productAttributes) {
        Product.ProductBuilder productBuilder = new Product.ProductBuilder(productAttributes.get("name"))
                .setDescription(productAttributes.get("description"))
                .setPrice(Integer.parseInt(productAttributes.get("price")))
                .setStock(Integer.parseInt(productAttributes.get("stock")))
                .setDiscount(Integer.parseInt(productAttributes.get("discount")))
                .setAvailability(productAttributes.get("availability"));
        Product product = productBuilder.build();
        if (!product.isValid()) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(productService.update(productId, product));
        }
    }

    @DeleteMapping("/delete-product/{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable String productId) {
        Product product = productService.findById(productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        } else {
            productService.deleteById(productId);
            return ResponseEntity.ok().build();
        }
    }
}