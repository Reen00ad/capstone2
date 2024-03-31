package com.example.capstone2.Service;

import com.example.capstone2.ApiResponce.ApiExeption;
import com.example.capstone2.Model.User;
import com.example.capstone2.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public void addUser(User user){

        userRepository.save(user);
    }

    public void updateUser(Integer id,User user){

        User u=userRepository.findUserById(id);

        if(u==null){
            throw new ApiExeption("wrong id");

        }
        u.setName(user.getName());
        u.setAge(user.getAge());
        u.setPhonenumber(user.getPhonenumber());
        u.setEmail(user.getEmail());
        u.setWeight(user.getWeight());
        u.setHeight(user.getHeight());

        userRepository.save(u);
    }

    public void deleteUser(Integer id){
        User u=userRepository.findUserById(id);
        if(u==null){
            throw new ApiExeption("wrong id");

        }
        userRepository.delete(u);

    }

    public Double getActivityMultiplier(Integer userid){
        User u=userRepository.findUserById(userid);
        switch (u.getActivitylevel()){
            case "sedentary":
                return  1.2;
            case "lightlyactive":
                return  1.375;
            case "moderatelyactive":
                return  1.55;
            case "veryactive":
                return  1.725;
            case "extraactive":
                return  1.9;
            default:
                return 1.0;
     }
    }

}
