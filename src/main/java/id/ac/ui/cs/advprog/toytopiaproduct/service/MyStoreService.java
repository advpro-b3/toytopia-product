package id.ac.ui.cs.advprog.toytopiaproduct.service;

import id.ac.ui.cs.advprog.toytopiaproduct.model.MyStore;
import id.ac.ui.cs.advprog.toytopiaproduct.model.Product;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface MyStoreService {
    CompletableFuture<MyStore> createMyStore(Long userId, Product product);

    CompletableFuture<Product> findById(Long userId, String productId);

    CompletableFuture<List<Product>> findAll(Long userId);

    CompletableFuture<Product> update(Long userId, String productId, Product updatedProduct);

    CompletableFuture<Product> deleteById(Long userId, String productId);
}
