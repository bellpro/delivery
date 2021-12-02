package com.bellpro.delivery.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class OrderResponseDto {
    private String restaurantName;      // 주문 음식점 이름
    private List<FoodOrderDto> foods;   // 주문 음식 정보
    private int deliveryFee;            // 배달비
    private int totalPrice;             // 최종 결제 금액
}
