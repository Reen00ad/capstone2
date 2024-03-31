package com.example.capstone2.Service;

import com.example.capstone2.ApiResponce.ApiExeption;
import com.example.capstone2.Model.Coatch;
import com.example.capstone2.Model.Food;
import com.example.capstone2.Repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;

    public List<Food> getAllFood(){
        return foodRepository.findAll();
    }

    public void addFood(Food food){
        foodRepository.save(food);
    }

    public void updateFood(Integer id,Food food){

        Food f=foodRepository.findFoodById(id);

        if(f==null){
            throw new ApiExeption("wrong id");

        }

        f.setName(food.getName());
        f.setDescription(food.getDescription());
        f.setCalories(food.getCalories());


        foodRepository.save(f);
    }

    public void deleteFood(Integer id){
        Food f=foodRepository.findFoodById(id);

        if(f==null){
            throw new ApiExeption("wrong id");

        }
        foodRepository.delete(f);
    }

    public List<Food> getFoodlessthan(Double cal){
        return foodRepository.findFoodByCaloriesIsLessThanEqual(cal);
    }


}
