package com.example.capstone2.Service;

import com.example.capstone2.ApiResponce.ApiExeption;
import com.example.capstone2.Model.Coatch;
import com.example.capstone2.Model.Gym;
import com.example.capstone2.Model.User;
import com.example.capstone2.Repository.CoachRepository;
import com.example.capstone2.Repository.GymRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoachService {

    private final CoachRepository coachRepository;
    private final GymRepository gymRepository;


    public List<Coatch> getAllCoach(){
        return coachRepository.findAll();
    }

    public void addCoach(Coatch coatch){
        Gym g=gymRepository.findGymById(coatch.getGymid());

        if(g==null){
            throw new ApiExeption("gym id not found");
        }

        coachRepository.save(coatch);
    }

    public void updateCoach(Integer id,Coatch coatch){

        Coatch c=coachRepository.findCoatchById(id);

        if(c==null){
            throw new ApiExeption("wrong id");

        }

        c.setName(coatch.getName());
        c.setPhonenumber(coatch.getPhonenumber());
        c.setSpecialization(coatch.getSpecialization());
        c.setGymid(coatch.getGymid());

        coachRepository.save(c);
    }

    public void deleteCoach(Integer id){
        Coatch c=coachRepository.findCoatchById(id);

        if(c==null){
            throw new ApiExeption("wrong id");

        }
        coachRepository.delete(c);

    }

    public List<Coatch> getCoachwithoutclasses(){
        return coachRepository.findCoatchWithoutClasses();
    }


    public List<Coatch> getBySpecializationAsc(Integer gymid){
        Gym g=gymRepository.findGymById(gymid);
        if(g==null){
            throw new ApiExeption("wrong id");
        }
        return coachRepository.findCoatchByGymidOrderBySpecializationAsc(gymid);
    }

}
