package id.ac.ui.cs.advprog.toytopiaproduct.service;

import id.ac.ui.cs.advprog.toytopiaproduct.model.Product;

import java.util.List;

public interface ProductService {
    Product create(Product product);
    Product findById(String productId);
    List<Product> findAll();
    Product update(String productId, Product updatedProduct);
    void deleteById(String productId);
}
