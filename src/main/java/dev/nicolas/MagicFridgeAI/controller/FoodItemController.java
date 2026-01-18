package dev.nicolas.MagicFridgeAI.controller;

import dev.nicolas.MagicFridgeAI.model.FoodItem;
import dev.nicolas.MagicFridgeAI.service.FoodItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    private FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    //GET

    //POST
    public ResponseEntity<FoodItem> createFoodItem(@RequestBody FoodItem foodItem) {
        FoodItem salvo = service.save(foodItem);

        return ResponseEntity.ok(salvo);
    }

    //UPDATE

    //DELETE

}
