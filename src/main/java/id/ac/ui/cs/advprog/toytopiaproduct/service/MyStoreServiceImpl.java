package id.ac.ui.cs.advprog.toytopiaproduct.service;

import id.ac.ui.cs.advprog.toytopiaproduct.model.MyStore;
import id.ac.ui.cs.advprog.toytopiaproduct.model.Product;
import id.ac.ui.cs.advprog.toytopiaproduct.repository.MyStoreRepository;
import id.ac.ui.cs.advprog.toytopiaproduct.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class MyStoreServiceImpl implements MyStoreService {

    private MyStoreRepository myStoreRepository;

    private ProductRepository productRepository;

    @Override
    public CompletableFuture<MyStore> createMyStore(Long userId, Product product) {
        MyStore myStore = myStoreRepository.findMyStoreById(userId);
        Map<String, Product> myStoreProducts = myStore.getAllProduct();

        if (myStoreProducts == null) {
            myStoreProducts = new HashMap<>();
            myStoreProducts.put(product.getId(), product);
        } else {
            myStoreProducts.put(product.getId(), product);
            myStore.setProductMap(myStoreProducts);
            myStoreRepository.save(myStore);
        }
        return CompletableFuture.completedFuture(myStore);
    }

    @Override
    public CompletableFuture<Product> findById(Long userId, String productId) {
        return CompletableFuture.completedFuture(myStoreRepository.findMyStoreById(userId).getAllProduct().get(productId));
    }

    @Override
    public CompletableFuture<List<Product>> findAll(Long userId) {
        return CompletableFuture.completedFuture(myStoreRepository.findMyStoreById(userId).getAllProduct().values().stream().toList());
    }

    @Override
    public CompletableFuture<Product> update(Long userId, String productId, Product updatedProduct) {
        return CompletableFuture.completedFuture(productRepository.save(updatedProduct));
    }

    @Override
    public CompletableFuture<Product> deleteById(Long userId, String productId) {
        return CompletableFuture.completedFuture(myStoreRepository.findMyStoreById(userId).getAllProduct().remove(productId));
    }
}
