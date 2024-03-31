package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponce.ApiResponce;
import com.example.capstone2.Model.Food;
import com.example.capstone2.Model.Gym;
import com.example.capstone2.Service.GymService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/gym")
@RequiredArgsConstructor
public class GymController {

    private final GymService gymService;


    @GetMapping("/get")
    public ResponseEntity getAllGym(){

        return ResponseEntity.status(200).body(gymService.getALlGym());
    }

    @PostMapping("/add")
    public ResponseEntity addFood(@RequestBody @Valid Gym gym, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        gymService.addGym(gym);
        return ResponseEntity.status(200).body(new ApiResponce("gym added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateGym(@PathVariable Integer id, @RequestBody @Valid Gym gym, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        gymService.updateGym(id,gym);
        return ResponseEntity.status(200).body(new ApiResponce("gym updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteGym(@PathVariable Integer id){
        gymService.deleteGym(id);

        return ResponseEntity.status(200).body(new ApiResponce("gym deleted"));

    }




}
