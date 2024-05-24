package id.ac.ui.cs.advprog.toytopiaproduct.model;

import id.ac.ui.cs.advprog.toytopiaproduct.enums.Availability;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    Product product;

    @BeforeEach
    void SetUp() {
        Product.ProductBuilder productBuilder = new Product.ProductBuilder("Hot Wheels 18 Camaro SS");
        product = productBuilder.setDescription("The  '18 Camaro SS is based on Hot Wheels' 50th Anniversary SEMA 2017 auto show in Las Vegas. A casting designed by Brendon Vetuskey with an initial-release color of Crush Orange.")
                .setPrice(25000)
                .setStock(20)
                .setDiscount(10)
                .setAvailability(Availability.READY.getValue())
                .build();
    }

    @Test
    void tesInvalidProduct() {
        assertTrue(product.isValid());
        // test name
        product.setName(null);
        assertFalse(product.isValid());
        product.setName("");
        assertFalse(product.isValid());
        product.setName("Hot Wheels 18 Camaro SS and longer name is invalid so that you cant make it so lengthy especially more than 255 characters is not allowed Hot Wheels 18 Camaro SS and longer name is invalid so that you cant make it so lengthy especially more than 255 characters is not allowed Hot Wheels 18 Camaro SS and longer name is invalid so that you cant make it so lengthy especially more than 255 characters is not allowed");
        assertFalse(product.isValid());
        product.setName("Hot Wheels 18 Camaro SS");
        assertTrue(product.isValid());
        // test description
        product.setDescription(null);
        assertFalse(product.isValid());
        product.setDescription("");
        assertFalse(product.isValid());
        product.setDescription("The  '18 Camaro SS is based on Hot Wheels' 50th Anniversary SEMA 2017 auto show in Las Vegas. The  '18 Camaro SS is based on Hot Wheels' 50th Anniversary SEMA 2017 auto show in Las Vegas.The  '18 Camaro SS is based on Hot Wheels' 50th Anniversary SEMA 2017 auto show in Las Vegas.The  '18 Camaro SS is based on Hot Wheels' 50th Anniversary SEMA 2017 auto show in Las Vegas.The  '18 Camaro SS is based on Hot Wheels' 50th Anniversary SEMA 2017 auto show in Las Vegas. The  '18 Camaro SS is based on Hot Wheels' 50th Anniversary SEMA 2017 auto show in Las Vegas.");
        assertFalse(product.isValid());
        product.setDescription("The  '18 Camaro SS is based on Hot Wheels' 50th Anniversary SEMA 2017 auto show in Las Vegas.");
        assertTrue(product.isValid());
        // test price
        product.setPrice(0);
        assertFalse(product.isValid());
        product.setPrice(1);
        assertTrue(product.isValid());
        // test stock
        product.setStock(-1);
        assertFalse(product.isValid());
        product.setStock(1);
        assertTrue(product.isValid());
        // test discount
        product.setDiscount(-1);
        assertFalse(product.isValid());
        product.setDiscount(101);
        assertFalse(product.isValid());
        product.setDiscount(100);
        assertTrue(product.isValid());
        product.setDiscount(0);
        assertTrue(product.isValid());
        // test availability
        product.setAvailability(null);
        assertFalse(product.isValid());
        product.setAvailability("");
        assertFalse(product.isValid());
        product.setAvailability(Availability.READY.getValue());
        assertTrue(product.isValid());
    }

    @Test
    void testGetId() {
        product.setId("1234567890");
        assertEquals("1234567890", this.product.getId());
    }

    @Test
    void testGetName() {
        assertEquals("Hot Wheels 18 Camaro SS", this.product.getName());
    }

    @Test
    void testGetDescription() {
        assertEquals("The  '18 Camaro SS is based on Hot Wheels' 50th Anniversary SEMA 2017 auto show in Las Vegas. A casting designed by Brendon Vetuskey with an initial-release color of Crush Orange.", this.product.getDescription());
    }

    @Test
    void testGetPrice() {
        assertEquals(25000, this.product.getPrice());
    }

    @Test
    void testGetStock() {
        assertEquals(20, this.product.getStock());
    }

    @Test
    void testGetDiscount() {
        assertEquals(10, this.product.getDiscount());
    }

    @Test
    void testGetAvailability() {
        assertEquals(Availability.READY.getValue(), this.product.getAvailability());
    }

    @Test
    void testSetAvailabilityToPreorder() {
        this.product.setAvailability(Availability.PREORDER.getValue());
        assertEquals(Availability.PREORDER.getValue(), this.product.getAvailability());
    }
}