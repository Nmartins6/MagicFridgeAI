package dev.nicolas.MagicFridgeAI.controller;

import dev.nicolas.MagicFridgeAI.model.FoodItem;
import dev.nicolas.MagicFridgeAI.service.FoodItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    private FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    //GET
    @RequestMapping("/list")
    public ResponseEntity<List<FoodItem>> FindAlll() {
        List<FoodItem> foodList = foodItemService.findAll();

        if (foodList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(foodList);
        }
    }

    @RequestMapping("/list/{id}")
    public ResponseEntity<FoodItem> findById(@PathVariable Long id) {
        FoodItem foodItem = foodItemService.findById(id);

        if (foodItem != null) {
            return ResponseEntity.ok(foodItem);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    //POST
    @RequestMapping("/create")
    public ResponseEntity<FoodItem> createFoodItem(@RequestBody FoodItem foodItem) {
        FoodItem created = foodItemService.save(foodItem);
        return ResponseEntity.ok(created);
    }

    //UPDATE
    @RequestMapping("/update/{id}")
    public ResponseEntity<?> updateFood(@PathVariable Long id, @RequestBody FoodItem updatedFoodItem) {
        if (foodItemService.findById(id) != null) {
            foodItemService.updateFood(id, updatedFoodItem);

            return ResponseEntity.ok(foodItemService.findById(id).getName() + " alterado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //DELETE
    @RequestMapping("delete/{id}")
    public ResponseEntity<String> deleteFood(@PathVariable Long id) {
        if (foodItemService.findById(id) != null) {
            foodItemService.deleteFood(id);
            return ResponseEntity.ok("Deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
