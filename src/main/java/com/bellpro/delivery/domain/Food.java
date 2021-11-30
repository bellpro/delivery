package com.bellpro.delivery.domain;

import com.bellpro.delivery.dto.FoodDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // get 메소드 자동 생성
@NoArgsConstructor  // 기본 생성자 자동 생성
@Entity     // DB 테이블 설정
public class Food {
    @Id // 기본키 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 자동으로 인덱스 증가
    private Long id;

    @Column(nullable = false)           // 열 설정 (무조건 입력)
    private Long restaurantId;          // 음식점 ID

    @Column(nullable = false)           // 열 설정 (무조건 입력)
    private String name;                // 음식명

    @Column(nullable = false)           // 열 설정 (무조건 입력)
    private int price;                  // 가격

    // 음식점 dto 생성자
    public Food(Long restaurantId, FoodDto foodDto){
        this.restaurantId = restaurantId;
        this.name = foodDto.getName();
        this.price = foodDto.getPrice();
    }
}
