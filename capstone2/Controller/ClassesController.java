package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponce.ApiResponce;
import com.example.capstone2.Model.Classes;
import com.example.capstone2.Model.Coatch;
import com.example.capstone2.Service.ClassesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@RestController
@RequestMapping("/api/v1/classes")
@RequiredArgsConstructor
public class ClassesController {

    private final ClassesService classesService;

    @GetMapping("/get")
    public ResponseEntity getAllClasses(){

        return ResponseEntity.status(200).body(classesService.getAllClasses());
    }

    @PostMapping("/add")
    public ResponseEntity addClasses(@RequestBody @Valid Classes classes, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        classesService.addClasses(classes);
        return ResponseEntity.status(200).body(new ApiResponce("classes added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateClasses(@PathVariable Integer id,@RequestBody @Valid Classes classes,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        classesService.updateClasses(id,classes);
        return ResponseEntity.status(200).body(new ApiResponce("classes updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteClasses(@PathVariable Integer id){
        classesService.deleteClasses(id);

        return ResponseEntity.status(200).body(new ApiResponce("classes deleted"));

    }

    @GetMapping("/fully")
    public ResponseEntity<List<Classes>> getfullybooked(){
        return ResponseEntity.status(200).body(classesService.getFullyBooked());
    }


    @GetMapping("/available")
    public ResponseEntity<List<Classes>> getEmptyClasses(){
        return ResponseEntity.status(200).body(classesService.getAvailableClasses());
    }

    @GetMapping("/upcoming")
    public ResponseEntity<List<Classes>> getUpcomingclasses(){
        return ResponseEntity.status(200).body(classesService.getUpcomingClasses());
    }

    @GetMapping("/time/{time}")
    public ResponseEntity<List<Classes>> getClassesStartAt(@PathVariable Time time){
        return ResponseEntity.status(200).body(classesService.getClassesStartAt(time));
    }


    @GetMapping("/byname/{cname}")
    public ResponseEntity<List<Classes>> getClassesbycoachname(@PathVariable String cname){
        return ResponseEntity.status(200).body(classesService.getClassesbycoachname(cname));
    }

}
