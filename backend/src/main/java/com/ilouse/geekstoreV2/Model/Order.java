package com.ilouse.geekstoreV2.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ilouse.geekstoreV2.enumm.City;

import com.ilouse.geekstoreV2.enumm.DeliveryStatus;
import com.ilouse.geekstoreV2.enumm.PaymentMethod;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime date;

    @ManyToMany
    private List<Product> products;

    @ManyToOne
    private User client;

    @Column
    private Float totalPrice;

    @Column
    private String addressLine;

    @Enumerated(EnumType.ORDINAL)
    private City city;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private PaymentMethod paymentMethod;

    @Column
    private String paymentInfo;

    @Column
    private Boolean isPaid;


    @OneToMany(
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<OrderItem> orderItems;

    @Column
    private DeliveryStatus deliveryStatus;

    public Order() {
    }

    public Order(Long id, LocalDateTime date, List<Product> products, User client, Float totalPrice, String addressLine, City city, PaymentMethod paymentMethod, String paymentInfo, Boolean isPaid, List<OrderItem> orderItems, DeliveryStatus deliveryStatus) {
        this.id = id;
        this.date = date;
        this.products = products;
        this.client = client;
        this.totalPrice = totalPrice;
        this.addressLine = addressLine;
        this.city = city;
        this.paymentMethod = paymentMethod;
        this.paymentInfo = paymentInfo;
        this.isPaid = isPaid;
        this.orderItems = orderItems;
        this.deliveryStatus = deliveryStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public Boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", products=" + products +
                ", client=" + client +
                ", totalPrice=" + totalPrice +
                ", addressLine='" + addressLine + '\'' +
                ", city=" + city +
                ", paymentMethod=" + paymentMethod +
                ", paymentInfo='" + paymentInfo + '\'' +
                ", isPaid=" + isPaid +
                ", orderItems=" + orderItems +
                ", deliveryStatus=" + deliveryStatus +
                '}';
    }
}

