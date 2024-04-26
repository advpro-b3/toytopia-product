package id.ac.ui.cs.advprog.toytopiaproduct;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class ToytopiaProductApplicationTests {
    @Test
    void contextLoads() {
    }

    @Test
    void mainTest(){
        assertDoesNotThrow(() -> ToytopiaProductApplication.main(new String[]{}));
    }
}
