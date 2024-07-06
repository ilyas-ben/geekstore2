package com.ilouse.geekstoreV2.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Review {
    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = true)
        private String comment;

        @Column
        private Integer rate;

        @JsonIgnore
        @ManyToOne
        private User client;


        @ManyToOne
        private Product product;

    public Review() {
    }

    public Review(Long id, String comment, Integer rate, User client) {
        this.id = id;
        this.comment = comment;
        this.rate = rate;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Review{" +
                "idAvis=" + id +
                ", commentaire='" + comment + '\'' +
                ", rate=" + rate +
                ", client=" + client +
                '}';
    }
}
