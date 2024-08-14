package com.ilouse.geekstoreV2.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.ToString;

@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private Product product;

    private int quantity;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;

    public CartItem() {
    }

    public CartItem(Long id, Product product, int quantity, Cart cart) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.cart = cart;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", cart=" + cart.getId() +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
