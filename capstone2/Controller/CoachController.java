package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponce.ApiResponce;
import com.example.capstone2.Model.Coatch;
import com.example.capstone2.Service.CoachService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/coach")
@RequiredArgsConstructor
public class CoachController {

    private final CoachService coachService;

    @GetMapping("/get")
    public ResponseEntity getAllCoach(){

        return ResponseEntity.status(200).body(coachService.getAllCoach());
    }

    @PostMapping("/add")
    public ResponseEntity addCoach(@RequestBody @Valid Coatch coatch, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        coachService.addCoach(coatch);
        return ResponseEntity.status(200).body(new ApiResponce("coach added"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateCoach(@PathVariable Integer id,@RequestBody @Valid Coatch coatch,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        coachService.updateCoach(id,coatch);
        return ResponseEntity.status(200).body(new ApiResponce("coach updated"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCoach(@PathVariable Integer id){
        coachService.deleteCoach(id);

        return ResponseEntity.status(200).body(new ApiResponce("coach deleted"));

    }

    @GetMapping("/without")
    public ResponseEntity<List<Coatch>> getCoachwithoutclasses(){
        return ResponseEntity.status(200).body(coachService.getCoachwithoutclasses());
    }

    @GetMapping("/specialization/{gymid}")
    public ResponseEntity<List<Coatch>> getBySpecializationAsc(@PathVariable Integer gymid){
        return ResponseEntity.status(200).body(coachService.getBySpecializationAsc(gymid));
    }
}
