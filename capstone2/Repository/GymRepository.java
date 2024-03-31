package com.example.capstone2.Repository;

import com.example.capstone2.Model.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymRepository extends JpaRepository<Gym,Integer> {

    Gym findGymById(Integer id);
}
