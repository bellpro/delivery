package com.bellpro.delivery.domain;

import com.bellpro.delivery.dto.RestaurantDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // get 메소드 자동 생성
@NoArgsConstructor  // 기본 생성자 자동 생성
@Entity     // DB 테이블 설정
public class Restaurant {   // 생성날짜/수정날짜 상속 받음
    @Id // 기본키 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 자동으로 인덱스 증가
    private Long id;

    @Column(nullable = false)    // 열 설정 (무조건 입력, 중복 허용 X)
    private String name;              // 음식점 이름

    @Column(nullable = false)                   // 열 설정 (무조건 입력)
    private int minOrderPrice;                  // 최소주문

    @Column(nullable = false)                   // 열 설정 (무조건 입력)
    private int deliveryFee;                    // 배달비

    // 음식점 dto 생성자
    public Restaurant(RestaurantDto restaurantDto){
        this.name = restaurantDto.getName();
        this.minOrderPrice = restaurantDto.getMinOrderPrice();
        this.deliveryFee = restaurantDto.getDeliveryFee();
    }

}
