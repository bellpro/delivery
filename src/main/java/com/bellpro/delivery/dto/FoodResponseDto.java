package com.bellpro.delivery.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter // set 메소드 자동 생성
@Getter // get 메소드 자동 생성
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 생성
@NoArgsConstructor  // 기본 생성자 자동 생성
public class FoodResponseDto {
    Long id;
    private String name;            // 음식명
    private int price;              // 가격

}
