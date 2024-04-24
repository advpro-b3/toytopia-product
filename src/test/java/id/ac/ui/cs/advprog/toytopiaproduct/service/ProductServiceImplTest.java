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
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

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
        Product.ProductBuilder productBuilder1 = new Product.ProductBuilder("1234567890", "Hot Wheels 18 Camaro SS");
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
        when(productRepository.create(any(Product.class))).thenReturn(product);
        assertEquals(product, productService.create(product));
    }

    @Test
    void findById() {
        Product product = products.getFirst();
        when(productRepository.findById(product.getId())).thenReturn(product);
        assertEquals(product, productService.findById(product.getId()));
    }

    @Test
    void findAll() {
        when(productRepository.findAll()).thenReturn(products.iterator());
        assertEquals(products.size(), productService.findAll().size());
    }

    @Test
    void update() {
        Product product = products.getFirst();
        Product updatedProduct = new Product();
        updatedProduct.setName("Hot Wheels Becak SS");
        updatedProduct.setDescription("Becak");
        when(productRepository.update(product.getId(), updatedProduct)).thenReturn(updatedProduct);

        productService.update(product.getId(), updatedProduct);
        verify(productRepository, times(1)).update(product.getId(), updatedProduct);
    }
}