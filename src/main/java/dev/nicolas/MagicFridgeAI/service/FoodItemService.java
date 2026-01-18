package dev.nicolas.MagicFridgeAI.service;

import dev.nicolas.MagicFridgeAI.model.FoodItem;
import dev.nicolas.MagicFridgeAI.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodItemService {

    private final FoodItemRepository foodItemRepository;

    private FoodItemService(FoodItemRepository foodItemRepository) {
        this.foodItemRepository = foodItemRepository;
    }

    public FoodItem save(FoodItem foodItem) {
        return foodItemRepository.save(foodItem);
    }

    public List<FoodItem> findAll() {
        return foodItemRepository.findAll();
    }

    public FoodItem findById(Long id) {
        Optional<FoodItem> foodItem = foodItemRepository.findById(id);

        return foodItem.orElse(null);
    }

    public void deleteFood(Long id) {
        foodItemRepository.deleteById(id);
    }

    public FoodItem updateFood(Long id, FoodItem updatedFoodItem) {
        if (foodItemRepository.findById(id).isPresent()) {
            updatedFoodItem.setId(id);
            foodItemRepository.save(updatedFoodItem);

            return updatedFoodItem;
        } else {
            return null;
        }

    }
}
