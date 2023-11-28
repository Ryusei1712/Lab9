package com.example.lab09.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "userOrder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNumber;
    private float totalSellingPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private List<Product> productList;
    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + id +
                ", orderNumber='" + orderNumber + '\'' +
                ", totalSellingPrice=" + totalSellingPrice +
                ", user=" + user +
                ", productList=" + productList +
                '}';
    }
}