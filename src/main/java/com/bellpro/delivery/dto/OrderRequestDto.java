package com.bellpro.delivery.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderRequestDto {
    private Long restaurantId;                  // 음식점 ID
    private List<FoodOrderRequestDto> foods;    // 음식 주문 정보
}
