package id.ac.ui.cs.advprog.toytopiaproduct.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import id.ac.ui.cs.advprog.toytopiaproduct.enums.Availability;
import id.ac.ui.cs.advprog.toytopiaproduct.model.Product;
import id.ac.ui.cs.advprog.toytopiaproduct.repository.ProductRepository;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    List<Product> products;

    @BeforeEach
    void setUp(){
        products = new ArrayList<>();
        Product.ProductBuilder productBuilder1 = new Product.ProductBuilder("Hot Wheels 18 Camaro SS");
        Product product1 = productBuilder1.setDescription("The  '18 Camaro SS is based on Hot Wheels' 50th Anniversary SEMA 2017 auto show in Las Vegas. A casting designed by Brendon Vetuskey with an initial-release color of Crush Orange. The Sixth Generation Camaro Hot Wheels Anniversary Special Edition was created by a team of designers led by Tom Peters.")
                .setPrice(25000)
                .setStock(20)
                .setDiscount(10)
                .setAvailability(Availability.READY.getValue())
                .build();
        products.add(product1);
    }

    @Test
    void create() {
        Product product = products.getFirst();
        when(productRepository.save(any(Product.class))).thenReturn(product);
        CompletableFuture<Product> productAsync = productService.create(product);
        Product createdProduct = productAsync.join();
        assertEquals(product, createdProduct);
    }

    @Test
    void findById() {
        Product product = products.getFirst();
        when(productRepository.findProductById(product.getId())).thenReturn(product);
        CompletableFuture<Product> productAsync = productService.findById(product.getId());
        Product foundProduct = productAsync.join();
        assertEquals(product, foundProduct);
    }

    @Test
    void findAll() {
        when(productRepository.findAll()).thenReturn(products);
        CompletableFuture<List<Product>> productListAsync = productService.findAll();

        // Retrieve the List<Product> from the CompletableFuture
        try {
            List<Product> productList = productListAsync.get(); // This will block until the result is available
            // Do something with the productList
            assertEquals(products.size(), productList.size());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
    }

    @Test
    void update() {
        Product product = products.getFirst();

        Product updatedProduct = new Product();
        updatedProduct.setName("Hot Wheels Becak SS");
        updatedProduct.setDescription("Becak");

        when(productRepository.findProductById(product.getId())).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);

        CompletableFuture<Product> updateProductAsync = productService.update(product.getId(), updatedProduct);
        Product updatedProductComplete = updateProductAsync.join();

        assertEquals(product, updatedProductComplete);

        assertEquals(updatedProduct.getName(), product.getName());
        assertEquals(updatedProduct.getDescription(), product.getDescription());
    }

    @Test
    void deleteById() {
        Product product = products.getFirst();
        productService.deleteById(product.getId());
        verify(productRepository, times(1)).deleteById(product.getId());
    }
}