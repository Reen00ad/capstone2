package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponce.ApiResponce;
import com.example.capstone2.Model.Classes;
import com.example.capstone2.Model.MealPlaning;
import com.example.capstone2.Service.MealPlaningService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mealplaning")
@RequiredArgsConstructor
public class MealPlaningController {

    private final MealPlaningService mealPlaningService;


    @GetMapping("/get")
    public ResponseEntity getAllMealPlaning(){

        return ResponseEntity.status(200).body(mealPlaningService.getAllMealPlaning());
    }

    @PostMapping("/add")
    public ResponseEntity addMealPlaning(@RequestBody @Valid MealPlaning mealPlaning, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        mealPlaningService.addMealPlaning(mealPlaning);
        return ResponseEntity.status(200).body(new ApiResponce("MealPlaning added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMealPlaning(@PathVariable Integer id, @RequestBody @Valid MealPlaning mealPlaning, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        mealPlaningService.updateMealPlaning(id,mealPlaning);
        return ResponseEntity.status(200).body(new ApiResponce("MealPlaning updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMealPlaning(@PathVariable Integer id){
        mealPlaningService.deleteMealPlaning(id);

        return ResponseEntity.status(200).body(new ApiResponce("MealPlaning deleted"));

    }

    @GetMapping("/calculate/{userid}")
    public ResponseEntity calculateCalorise(@PathVariable Integer userid){
        return ResponseEntity.status(200).body(mealPlaningService.calculateCalorise(userid));
    }

    @GetMapping("/bmi/{userid}")
    public ResponseEntity calculateBMI(@PathVariable Integer userid){
        return ResponseEntity.status(200).body(mealPlaningService.calculateBMI(userid));
    }

    @GetMapping("/checkforfood/{userid}/{foodid}")
    public ResponseEntity checkForFoodCal(@PathVariable Integer userid, @PathVariable Integer foodid){
        return ResponseEntity.status(200).body(mealPlaningService.checkForFoodCal(userid, foodid));
    }

    @GetMapping("/ideal/{userid}")
    public ResponseEntity idealweight(@PathVariable Integer userid){
        return ResponseEntity.status(200).body(mealPlaningService.getIdealWeight(userid));
    }






}
