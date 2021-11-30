package com.bellpro.delivery.validator;

import com.bellpro.delivery.dto.FoodDto;
import org.springframework.stereotype.Component;

@Component  // Bean 등록
public class FoodDtoValidator {

    public static void validateFoodDto(FoodDto foodDto){

        if (foodDto.getPrice() < 100 || foodDto.getPrice() > 1000000 ){
            throw new IllegalArgumentException("가격은 100원 ~ 1,000,000원을 입력해주세요.");
        } else if (foodDto.getPrice() % 100 != 0){
            throw new IllegalArgumentException("가격은 100원 단위로만 입력 가능합니다.");
        }
    }
}
