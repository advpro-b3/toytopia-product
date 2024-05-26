package id.ac.ui.cs.advprog.toytopiaproduct.service;

import id.ac.ui.cs.advprog.toytopiaproduct.model.Product;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ProductService {
    CompletableFuture<Product> create(Product product);

    CompletableFuture<Product> findById(String productId);

    CompletableFuture<List<Product>> findAll();

    CompletableFuture<Product> update(String productId, Product updatedProduct);
    void deleteById(String productId);
}
