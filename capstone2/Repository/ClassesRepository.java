package com.example.capstone2.Repository;

import com.example.capstone2.Model.Classes;
import com.example.capstone2.Model.Coatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ClassesRepository extends JpaRepository<Classes,Integer> {

    Classes findClassesById(Integer id);


    List<Classes> findClassesByCapacity(Integer capacity);

    List<Classes> findClassesByCapacityGreaterThanEqual(Integer capacity);



    List<Classes> findClassesByDateAfter(LocalDate date);



    @Query("select cl from Classes cl where cl.coachid = (select c.id from Coatch c where c.name = :cname)")
    List<Classes> findCoatchByNames(String cname);

    List<Classes> findClassesByTimeEquals(Time time);





}
