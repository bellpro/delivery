package com.bellpro.delivery.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter // set 메소드 자동 생성
@Getter // get 메소드 자동 생성
@NoArgsConstructor  // 기본 생성자 자동 생성
public class RestaurantDto {
    private String name;                    // 음식점 이름
    private int minOrderPrice;              // 최소주문 가격
    private int deliveryFee;                // 기본 배달비
}
