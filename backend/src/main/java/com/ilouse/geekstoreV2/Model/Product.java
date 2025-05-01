package com.ilouse.geekstoreV2.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String imagePath;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Integer price;

    @Column
    private Integer quantityStock;

    @ManyToOne
    private Category category;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller; // who is sellling that product

    @Column(nullable = true)
    @JsonIgnore
    @OneToMany(
            mappedBy = "product",
            fetch = FetchType.LAZY
    )
    private List<Review> reviews;

    @Column(nullable = true)
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    private List<OrderItem> orderitems;


    public Product() {
    }

    public Product(
            Long id,
            String imagePath,
            String name,
            String description,
            Integer price,
            Category category,
            Integer quantityStock,
            User seller,
            List<Review> reviews,
            List<OrderItem> orderitems) {
        this.id = id;
        this.imagePath = imagePath;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.quantityStock = quantityStock;
        this.seller = seller;
        this.reviews = reviews;
        this.orderitems = orderitems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getQuantityStock() {
        return quantityStock;
    }

    public void setQuantityStock(Integer quantityStock) {
        this.quantityStock = quantityStock;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<OrderItem> getOrderitems() {
        return orderitems;
    }

    public void setOrderitems(List<OrderItem> orderitems) {
        this.orderitems = orderitems;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", imagePath='" + imagePath + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", quantityStock=" + quantityStock +
                ", seller=" + seller +
                ", reviews=" + reviews +
                ", orderitems=" + orderitems +
                '}';
    }
}
