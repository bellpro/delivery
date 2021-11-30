package com.bellpro.delivery.repository;

import com.bellpro.delivery.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface FoodRepository extends JpaRepository<Food, Long> {
    Optional<Food> findByRestaurantIdAndName(Long restaurantId, String name);   // 같은 음식점 중복 음식명 불가, 다른 음식점 가능
    List<Food> findByRestaurantId(Long restaurantId);
}