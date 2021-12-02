package com.bellpro.delivery.domain;

import com.bellpro.delivery.dto.FoodDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter // get 메소드 자동 생성
@NoArgsConstructor  // 기본 생성자 자동 생성
@Entity     // DB 테이블 설정
public class Food {
    @Id // 기본키 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 자동으로 인덱스 증가
    private Long id;

    @ManyToOne  // 다대일 (하나의 음식점에는 여러 개의 음식들이 있다)
    @JoinColumn(name = "restaurant_id", nullable = false) // 열 설정 (외래키 이름, 무조건 입력)
    Restaurant restaurant;              // 음식점

    @Column(nullable = false)           // 열 설정 (무조건 입력)
    private String name;                // 음식명

    @Column(nullable = false)           // 열 설정 (무조건 입력)
    private int price;                  // 가격

    // 음식점 dto 생성자
    public Food(Restaurant restaurant, FoodDto foodDto){
        this.restaurant = restaurant;
        this.name = foodDto.getName();
        this.price = foodDto.getPrice();
    }
}
