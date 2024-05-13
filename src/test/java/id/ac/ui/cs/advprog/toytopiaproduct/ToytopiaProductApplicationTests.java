package id.ac.ui.cs.advprog.toytopiaproduct;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ToytopiaProductApplicationTests {

    @Autowired
    private ToytopiaProductApplication toytopiaProductApplication;

    @Test
    void contextLoads() {
        assertNotNull(toytopiaProductApplication);
    }

    @SuppressWarnings("static-access")
    @Test
    void mainTest() {
        assertDoesNotThrow(() -> toytopiaProductApplication.main(new String[]{}));
    }
}
