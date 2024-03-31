package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponce.ApiResponce;
import com.example.capstone2.Model.Classes;
import com.example.capstone2.Model.Food;
import com.example.capstone2.Service.FoodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;


    @GetMapping("/get")
    public ResponseEntity getAllFood(){

        return ResponseEntity.status(200).body(foodService.getAllFood());
    }

    @PostMapping("/add")
    public ResponseEntity addFood(@RequestBody @Valid Food food, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        foodService.addFood(food);
        return ResponseEntity.status(200).body(new ApiResponce("food added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateFood(@PathVariable Integer id,@RequestBody @Valid Food food,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        foodService.updateFood(id,food);
        return ResponseEntity.status(200).body(new ApiResponce("food updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteFood(@PathVariable Integer id){
        foodService.deleteFood(id);

        return ResponseEntity.status(200).body(new ApiResponce("food deleted"));

    }

    @GetMapping("/bycal/{cal}")
    public ResponseEntity<List<Food>> getFoodlessthancal(@PathVariable Double cal){
        return ResponseEntity.status(200).body(foodService.getFoodlessthan(cal));
    }




}
