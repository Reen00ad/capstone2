package com.example.capstone2.Service;

import com.example.capstone2.ApiResponce.ApiExeption;
import com.example.capstone2.Model.Classes;
import com.example.capstone2.Model.Food;
import com.example.capstone2.Model.MealPlaning;
import com.example.capstone2.Model.User;
import com.example.capstone2.Repository.FoodRepository;
import com.example.capstone2.Repository.MealPlaningRepository;
import com.example.capstone2.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MealPlaningService {

    private final MealPlaningRepository mealPlaningRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final FoodRepository foodRepository;


    public List<MealPlaning> getAllMealPlaning(){
        return mealPlaningRepository.findAll();
    }

    public void addMealPlaning(MealPlaning mealPlaning){

        User u=userRepository.findUserById(mealPlaning.getUserid());
        Food f=foodRepository.findFoodById(mealPlaning.getFoodid());

            if(u==null ){
            if(f == null){
                throw new ApiExeption("food id not found");
            }
            throw new ApiExeption("user id not found");
        }

        mealPlaningRepository.save(mealPlaning);
    }

    public void updateMealPlaning(Integer id,MealPlaning mealPlaning){
        MealPlaning m=mealPlaningRepository.findMealPlaningById(id);

        if(m==null){
            throw new ApiExeption("not found");
        }

        m.setUserid(mealPlaning.getUserid());
        m.setFoodid(mealPlaning.getFoodid());

        mealPlaningRepository.save(m);
    }

    public void deleteMealPlaning(Integer id){
        MealPlaning m=mealPlaningRepository.findMealPlaningById(id);

        if(m==null){
            throw new ApiExeption("not found");
        }
        mealPlaningRepository.delete(m);
    }


    public Double calculateCalorise(Integer userid){
        User u =userRepository.findUserById(userid);

        if(u==null){
            throw new ApiExeption("not found");
        }
        Double bmr;

        if(u.getGender()=="male"){
            bmr = 10 * u.getWeight() + 6.25 * u.getHeight() - 5 * u.getAge() + 5;}
            else{
                bmr = 10 * u.getWeight() + 6.25 * u.getHeight() - 5 * u.getAge() - 161;
            }
             Double activitymultiplier = userService.getActivityMultiplier(userid);
             Double calories = bmr * activitymultiplier;

        return calories;

    }

    public String calculateBMI(Integer userid){
        User u =userRepository.findUserById(userid);
        if(u==null){
            throw new ApiExeption("not found");
        }
         Double height=u.getHeight() / 100;
         Double bmi=u.getWeight()/(height * height);


        String status;

        if(bmi < 18.5){
            status = "underweight";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            status = "normal weight";
        } else if (bmi >= 25 && bmi < 29.9) {
            status = "over weight";
        }
        else {
            status = "obese";
        }


        return "you BMI is : "+bmi+". You are "+status+" . ";
    }


    public String getIdealWeight(Integer userid){
        User u =userRepository.findUserById(userid);
        if(u==null){
            throw new ApiExeption("not found");
        }


        Double idealweight = null;

        if(u.getGender().equalsIgnoreCase("male")){
            idealweight = 50 + 0.91 * ( u.getHeight() - 152.4 );}
        else if(u.getGender().equalsIgnoreCase("female")){
            idealweight = 45.5 + 0.91 * ( u.getHeight() - 152.4 );
        }
        
        return " your perfect weight based on you BMI : "+idealweight;
    }

    public String checkForFoodCal(Integer userid,Integer foodid){
        User u=userRepository.findUserById(userid);
        Food f=foodRepository.findFoodById(foodid);

        if(u==null ){
            if(f == null){
                throw new ApiExeption("food id not found");
            }
            throw new ApiExeption("user id not found");
        }

        if(f.getCalories() > calculateCalorise(userid)){
            return " meal plan exceeds user's calorie intake ";
        }
        else
            return "meal plan is within user's calorie intake ";

    }


}
