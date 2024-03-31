package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponce.ApiResponce;
import com.example.capstone2.Model.Booking;
import com.example.capstone2.Model.Classes;
import com.example.capstone2.Service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping("/get")
    public ResponseEntity getAllBooking(){

        return ResponseEntity.status(200).body(bookingService.getAllBooking());
    }

    @PostMapping("/add")
    public ResponseEntity addBooking(@RequestBody @Valid Booking booking, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        bookingService.addBooking(booking);
        return ResponseEntity.status(200).body(new ApiResponce("booking class is done"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateBooking(@PathVariable Integer id,@RequestBody @Valid Booking booking,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        bookingService.updateBooking(id,booking);
        return ResponseEntity.status(200).body(new ApiResponce("booking updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBooking(@PathVariable Integer id){
        bookingService.deleteBooking(id);

        return ResponseEntity.status(200).body(new ApiResponce("cancel booking is done"));

    }


    @GetMapping("/getuserbooking/{userid}")
    public ResponseEntity<List<Booking>> getUserbooking(@PathVariable Integer userid){
        return ResponseEntity.status(200).body(bookingService.getUserbooking(userid));
    }

    
}
