package com.bellpro.delivery.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Ordering {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne  // 하나의 음식점에서 여러 개의 주문을 할 수 있다
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @OneToMany  // 하나의 주문에는 여러 개의 주문 목록을 넣을 수 있다
    @Column(nullable = false)
    private List<OrderFood> foods;

    @Column(nullable = false)
    private int totalPrice;

    public Ordering(Restaurant restaurant, List<OrderFood> foods, int totalPrice){
        this.restaurant = restaurant;
        this.foods = foods;
        this.totalPrice = totalPrice + restaurant.getDeliveryFee();
    }
}
