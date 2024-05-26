package id.ac.ui.cs.advprog.toytopiaproduct.service;

import id.ac.ui.cs.advprog.toytopiaproduct.model.Product;
import id.ac.ui.cs.advprog.toytopiaproduct.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Async
    public CompletableFuture<Product> create(Product product) {
        return CompletableFuture.completedFuture(productRepository.save(product));
    }

    @Override
    @Async
    public CompletableFuture<Product> findById(String productId) {
        return CompletableFuture.completedFuture(productRepository.findProductById(productId));
    }

    @Override
    @Async
    public CompletableFuture<List<Product>> findAll() {
        return CompletableFuture.completedFuture(productRepository.findAll());
    }

    @Override
    @Async
    public CompletableFuture<Product> update(String productId, Product updatedProduct) {
        Product product = productRepository.findProductById(productId);
        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        product.setStock(updatedProduct.getStock());
        product.setDiscount(updatedProduct.getDiscount());
        product.setAvailability(updatedProduct.getAvailability());
        return CompletableFuture.completedFuture(productRepository.save(product));
    }

    @Override
    public void deleteById(String productId) {
        productRepository.deleteById(productId);
    }
}
