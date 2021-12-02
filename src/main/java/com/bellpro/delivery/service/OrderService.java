package com.bellpro.delivery.service;

import com.bellpro.delivery.domain.Food;
import com.bellpro.delivery.domain.OrderFood;
import com.bellpro.delivery.domain.Ordering;
import com.bellpro.delivery.domain.Restaurant;
import com.bellpro.delivery.dto.FoodOrderDto;
import com.bellpro.delivery.dto.FoodOrderRequestDto;
import com.bellpro.delivery.dto.OrderRequestDto;
import com.bellpro.delivery.dto.OrderResponseDto;
import com.bellpro.delivery.repository.FoodRepository;
import com.bellpro.delivery.repository.OrderFoodRepository;
import com.bellpro.delivery.repository.OrderRepository;
import com.bellpro.delivery.repository.RestaurantRepository;
import com.bellpro.delivery.validator.OrderFoodValidator;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final OrderFoodRepository orderFoodRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public OrderResponseDto saveOrder(OrderRequestDto orderRequestDto) {
        // 음식점 존재하는지 확인
        Restaurant restaurant = restaurantRepository.findById(orderRequestDto.getRestaurantId())
                .orElseThrow(() -> new NullPointerException("해당 음식점이 존재하지 않습니다."));

        List<FoodOrderDto> foods = new ArrayList<>(); // 반환으로 줄 DTO에 담을 주문 foods
        List<OrderFood> orderFoods = new ArrayList<>(); // DB에 저장할 주문 foods
        int totalPrice = 0; // 총 가격
        for (FoodOrderRequestDto foodOrderRequestDto : orderRequestDto.getFoods()){
            // 주문한 음식이 존재하는지 확인
            Food food = foodRepository.findByIdAndRestaurant(foodOrderRequestDto.getId(), restaurant);
            if (food == null){
                throw new IllegalArgumentException("주문한 음식이 존재하지 않습니다.");
            }

            // 음식별 수량 가져오기
            int quantity = foodOrderRequestDto.getQuantity();
            OrderFoodValidator.validateOrderFood(quantity, food.getPrice());

            // 음식 주문 목록 생성, DB 저장
            OrderFood orderFood = new OrderFood(food, quantity);
            orderFoodRepository.save(orderFood);

            int price = orderFood.getPrice(); // 수량 * 음식 가격

            // 주문 음식 DTO 생성
            FoodOrderDto foodOrderDto = new FoodOrderDto(food.getName(), quantity, price);

            totalPrice += price;    // 총 가격 계산

            orderFoods.add(orderFood);  // DB 저장할 List
            foods.add(foodOrderDto);    // 빤환 음식 목록 List
        }
        OrderFoodValidator.validateOrderTotalPrice(restaurant.getMinOrderPrice(), totalPrice);
        Ordering ordering = new Ordering(restaurant, orderFoods, totalPrice);
        orderRepository.save(ordering);

        // 반환 dto
        return new OrderResponseDto(restaurant.getName(), foods, ordering.getRestaurant().getDeliveryFee(), ordering.getTotalPrice());
    }

    public List<OrderResponseDto> findOrder() {
        List<Ordering> orders = orderRepository.findAll();

        List<OrderResponseDto> orderList = new ArrayList<>();
        for (Ordering order : orders){
            List<FoodOrderDto> foodOrderDtoList = new ArrayList<>();
            for (OrderFood orderFood : order.getFoods()){
                Food food = orderFood.getFood();
                FoodOrderDto foodOrderDto = new FoodOrderDto(food.getName(), orderFood.getQuantity(), orderFood.getPrice());
                foodOrderDtoList.add(foodOrderDto);
            }
            Restaurant restaurant = order.getRestaurant();
            OrderResponseDto orderResponseDto = new OrderResponseDto(restaurant.getName(), foodOrderDtoList, restaurant.getDeliveryFee(), order.getTotalPrice());
            orderList.add(orderResponseDto);
        }
        return orderList;
    }
}
