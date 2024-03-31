package com.example.capstone2.Service;

import com.example.capstone2.ApiResponce.ApiExeption;
import com.example.capstone2.Model.Booking;
import com.example.capstone2.Model.Classes;
import com.example.capstone2.Model.Coatch;
import com.example.capstone2.Model.User;
import com.example.capstone2.Repository.BookingRepository;
import com.example.capstone2.Repository.ClassesRepository;
import com.example.capstone2.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ClassesRepository classesRepository;
    private final UserRepository userRepository;


    public List<Booking> getAllBooking(){
        return bookingRepository.findAll();
    }

    public void addBooking(Booking booking){

        Classes c=classesRepository.findClassesById(booking.getClassesid());
        User u=userRepository.findUserById(booking.getUserid());

        if(c==null ){
            if(u == null){
                throw new ApiExeption("user id not found");
            }
            throw new ApiExeption("classes id not found");
        }
        if(c.getCapacity() <= 0){
            throw new ApiExeption(" class is already fully booked");
        }

        booking=new Booking();
        booking.setUserid(u.getId());
        booking.setClassesid(c.getId());

        booking.setBookingtime(LocalDate.now());

        c.setCapacity(c.getCapacity() - 1);
        classesRepository.save(c);

        bookingRepository.save(booking);
    }

    public void updateBooking(Integer id,Booking booking){

        Booking b=bookingRepository.findBookingById(id);

        if(b==null){
            throw new ApiExeption("wrong id");

        }

        b.setUserid(booking.getUserid());
        b.setClassesid(booking.getClassesid());
        b.setBookingtime(booking.getBookingtime());


        bookingRepository.save(b);
    }


    public void deleteBooking(Integer id){
        Booking b=bookingRepository.findBookingById(id);

        if(b==null){
            throw new ApiExeption("wrong id");

        }

        Integer classids=b.getClassesid();

        Classes c =classesRepository.findClassesById(classids);

        if(c != null){
            c.setCapacity(c.getCapacity() + 1);
            classesRepository.save(c);
        }
        bookingRepository.delete(b);

    }

    public List<Booking> getUserbooking(Integer userid){
        User u=userRepository.findUserById(userid);

        if(u==null){
            throw new ApiExeption("not found");
        }
        return bookingRepository.findByUserid(userid);
    }


}
