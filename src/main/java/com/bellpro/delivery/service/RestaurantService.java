package com.bellpro.delivery.service;

import com.bellpro.delivery.domain.Restaurant;
import com.bellpro.delivery.dto.RestaurantDto;
import com.bellpro.delivery.repository.RestaurantRepository;
import com.bellpro.delivery.validator.RestaurantDtoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor    // 의존성 주입, final 필드에 대해 생성자를 생성,  @Autowired 대신 사용
@Service    // Service 명시(@Component 포함): Bean 등록
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Transactional  // 트랜잭션 처리
    public Restaurant saveRestaurant(RestaurantDto restaurantDto){
        // 음식점 이름 중복 검사
//        Optional<Restaurant> checkName = restaurantRepository.findByName(restaurantDto.getName());
//        if (checkName.isPresent()){
//            throw new IllegalArgumentException("중복된 음식점 이름입니다.");
//        }
        // 음식점 정보 유효성 검사 (최소주문 가격, 기본 배달비)
        RestaurantDtoValidator.validateRestaurantDto(restaurantDto);

        // 음식점 등록
        Restaurant restaurant = new Restaurant(restaurantDto);
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    public List<Restaurant> listRestaurants() {
        // 모든 음식점 조회
        return restaurantRepository.findAll();
    }
}
