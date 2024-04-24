package id.ac.ui.cs.advprog.toytopiaproduct.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private String id;
    private String name;
    private String description;
    private int price;
    private int stock;
    private int discount;
    private String availability;

    public static class ProductBuilder {
        private String id;
        private String name;
        private String description;
        private int price;
        private int stock;
        private int discount;
        private String availability;

        public ProductBuilder(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public ProductBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public ProductBuilder setPrice(int price) {
            this.price = price;
            return this;
        }

        public ProductBuilder setStock(int stock) {
            this.stock = stock;
            return this;
        }

        public ProductBuilder setDiscount(int discount) {
            this.discount = discount;
            return this;
        }

        public ProductBuilder setAvailability(String availability) {
            this.availability = availability;
            return this;
        }

        public Product build() {
            Product product = new Product();
            product.setId(this.id);
            product.setName(this.name);
            product.setDescription(this.description);
            product.setPrice(this.price);
            product.setStock(this.stock);
            product.setDiscount(this.discount);
            product.setAvailability(this.availability);
            return product;
        }
    }
}
