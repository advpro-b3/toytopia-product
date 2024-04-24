package id.ac.ui.cs.advprog.toytopiaproduct.controller;

import id.ac.ui.cs.advprog.toytopiaproduct.model.Product;
import id.ac.ui.cs.advprog.toytopiaproduct.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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
        products.add(new Product.ProductBuilder("1234567890", "Hot Wheels 18 Camaro SS").build());
        when(productService.findAll()).thenReturn(products);
        assertEquals(1, productApiController.getAllProducts().size());
    }

    @Test
    public void testGetProductById() {
        Product product = new Product.ProductBuilder("1234567890", "Hot Wheels 18 Camaro SS").build();
        when(productService.findById("1234567890")).thenReturn(product);
        assertEquals(product, productApiController.getProductById("1234567890"));
    }

}