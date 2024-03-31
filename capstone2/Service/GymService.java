package com.example.capstone2.Service;

import com.example.capstone2.ApiResponce.ApiExeption;
import com.example.capstone2.Model.Gym;
import com.example.capstone2.Repository.GymRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GymService {

    private final GymRepository gymRepository;

    public List<Gym> getALlGym(){
        return gymRepository.findAll();
    }

    public void addGym(Gym gym){
        gymRepository.save(gym);
    }

    public void updateGym(Integer id,Gym gym){
        Gym g=gymRepository.findGymById(id);

        if(g==null){
            throw new ApiExeption("not found");
        }

        g.setLocation(g.getLocation());

        gymRepository.save(g);
    }

    public void deleteGym(Integer id){
        Gym g=gymRepository.findGymById(id);

        if(g==null){
            throw new ApiExeption("not found");
        }

        gymRepository.delete(g);
    }


}
