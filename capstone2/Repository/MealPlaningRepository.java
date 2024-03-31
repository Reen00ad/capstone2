package com.example.capstone2.Repository;

import com.example.capstone2.Model.MealPlaning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealPlaningRepository extends JpaRepository<MealPlaning,Integer> {

    MealPlaning findMealPlaningById(Integer id);
}
