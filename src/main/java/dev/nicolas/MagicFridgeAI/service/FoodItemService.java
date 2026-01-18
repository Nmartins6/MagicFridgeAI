package dev.nicolas.MagicFridgeAI.service;

import dev.nicolas.MagicFridgeAI.model.FoodItem;
import dev.nicolas.MagicFridgeAI.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemService {

    private final FoodItemRepository foodItemRepository;

    private FoodItemService(FoodItemRepository foodItemRepository) {
        this.foodItemRepository = foodItemRepository;
    }

    public FoodItem save(FoodItem foodItem) {
        return foodItemRepository.save(foodItem);
    }

    public List<FoodItem> listAll() {
        return foodItemRepository.findAll();
    }
}
