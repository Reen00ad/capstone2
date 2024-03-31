package com.example.capstone2.Repository;

import com.example.capstone2.Model.Booking;
import com.example.capstone2.Model.Classes;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {

    Booking findBookingById(Integer id);

    List<Booking> findByUserid(Integer userid);


}
