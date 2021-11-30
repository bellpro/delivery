package com.bellpro.delivery.service;

import com.bellpro.delivery.domain.Food;
import com.bellpro.delivery.dto.FoodDto;
import com.bellpro.delivery.dto.FoodResponseDto;
import com.bellpro.delivery.repository.FoodRepository;
import com.bellpro.delivery.validator.FoodDtoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor    // 의존성 주입, final 필드에 대해 생성자를 생성,  @Autowired 대신 사용
@Service    // Service 명시(@Component 포함): Bean 등록
public class FoodService {
    private final FoodRepository foodRepository;

    // 음식명 등록
    @Transactional  // 트랜잭션 처리
    public List<Food> saveFood(Long restaurantId, List<FoodDto> foodDto){
        List<Food> foodList = new ArrayList<>();

        // 같은 음식점 음식명 중복 검사
        for (int i = 0; i < foodDto.size(); i++){
            Optional<Food> checkFoodName = foodRepository.findByRestaurantIdAndName(restaurantId, foodDto.get(i).getName());
            if (checkFoodName.isPresent()){
                throw new IllegalArgumentException("중복된 음식명이 존재합니다.");
            }
            // 음식 정보 유효성 검사 (가격)
            FoodDtoValidator.validateFoodDto(foodDto.get(i));

            // 음식 등록
            Food food = new Food(restaurantId, foodDto.get(i));
            foodRepository.save(food);
            foodList.add(food);
        }
        return foodList;
    }

    public List<FoodResponseDto> listFoods(Long restaurantId) {
        // 음식점별 음식명 조회
        List<Food> foodList = foodRepository.findByRestaurantId(restaurantId);

        // 음식명 Response List 생성 (restaurantId 제외하고 넣어야함)
        List<FoodResponseDto> foodResponseDtoList = new ArrayList<>();
        for (int i = 0; i < foodList.size(); i++){
            foodResponseDtoList.add(new FoodResponseDto(foodList.get(i).getId(), foodList.get(i).getName(), foodList.get(i).getPrice()));
        }
        return foodResponseDtoList;
    }
}
