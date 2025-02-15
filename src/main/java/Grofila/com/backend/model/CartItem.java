//package Grofila.com.backend.model;
//
//import Grofila.com.backend.model.Product;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//@Entity
//@Getter
//@Setter
//public class Cart {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public com.grofila.models.Cart getCart() {
//        return cart;
//    }
//
//    public void setCart(com.grofila.models.Cart cart) {
//        this.cart = cart;
//    }
//
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//    @ManyToOne
//    @JoinColumn(name = "cart_id", referencedColumnName = "id")
//    private com.grofila.models.Cart cart;
//
//    @ManyToOne
//    @JoinColumn(name = "product_id", referencedColumnName = "id")
//    private Product product;
//
//    private int quantity;
//    private double price;
//}