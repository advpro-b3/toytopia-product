package id.ac.ui.cs.advprog.toytopiaproduct.controller;

import id.ac.ui.cs.advprog.toytopiaproduct.model.Product;
import id.ac.ui.cs.advprog.toytopiaproduct.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductApiControllerTest {

    @Mock
    private ProductServiceImpl productService;

    @InjectMocks
    private ProductApiController productApiController;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testGetAllProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product.ProductBuilder("Hot Wheels 18 Camaro SS").build());
        when(productService.findAll()).thenReturn(products);
        assertNotNull(productApiController.getAllProducts().getBody());
        assertEquals(1, productApiController.getAllProducts().getBody().size());
    }

    @Test
    public void testGetProductById() {
        Product product = new Product.ProductBuilder("Hot Wheels 18 Camaro SS").build();
        product.setId("1234567890");
        when(productService.findById("1234567890")).thenReturn(product);
        assertNotNull(productApiController.getProductById("1234567890").getBody());
        assertEquals(product, productApiController.getProductById("1234567890").getBody());
    }

    @Test
    public void testCreateProduct() {
        // Prepare test data
        HashMap<String, String> productAttributes = new HashMap<>();
        productAttributes.put("name", "Test Product");
        productAttributes.put("description", "Test Description");
        productAttributes.put("price", "100");
        productAttributes.put("stock", "10");
        productAttributes.put("discount", "0");
        productAttributes.put("availability", "In stock");

        // Mock the behavior of productService.create to return a mocked product
        Product mockedProduct = new Product.ProductBuilder("Test Product")
                .setDescription("Test Description")
                .setPrice(100)
                .setStock(10)
                .setDiscount(0)
                .setAvailability("In stock")
                .build();
        when(productService.create(any(Product.class))).thenReturn(mockedProduct);

        // Call the controller method
        ResponseEntity<Product> createdProductResponse = productApiController.createProduct(productAttributes);
        Product createdProduct = createdProductResponse.getBody();

        // Verify that productService.create was called with the expected product
        verify(productService, times(1)).create(argThat(product ->
                product.getName().equals("Test Product") &&
                        product.getDescription().equals("Test Description") &&
                        product.getPrice() == 100 &&
                        product.getStock() == 10 &&
                        product.getDiscount() == 0 &&
                        product.getAvailability().equals("In stock")));

        // Verify that the returned product matches the mocked product
        assert createdProduct != null;
        assert createdProduct.getName().equals("Test Product");
        assert createdProduct.getDescription().equals("Test Description");
        assert createdProduct.getPrice() == 100;
        assert createdProduct.getStock() == 10;
        assert createdProduct.getDiscount() == 0;
        assert createdProduct.getAvailability().equals("In stock");
    }
}