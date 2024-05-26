package id.ac.ui.cs.advprog.toytopiaproduct.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@Entity
public class MyStore {
    @Id
    private Long userId;
    @OneToMany(mappedBy = "myStore", cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKey(name = "productId")
    private Map<String, Product> productMap;

    public MyStore() {
        this.productMap = new HashMap<>();
    }

    public void addProduct(Product product) {
        productMap.put(product.getId(), product);
    }

    public void removeProduct(String productId) {
        productMap.remove(productId);
    }

    public Map<String, Product> getAllProduct() {
        return productMap;
    }
}
