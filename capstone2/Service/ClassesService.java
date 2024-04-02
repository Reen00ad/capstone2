package com.example.capstone2.Service;

import com.example.capstone2.ApiResponce.ApiExeption;
import com.example.capstone2.Model.Classes;
import com.example.capstone2.Model.Coatch;
import com.example.capstone2.Repository.ClassesRepository;
import com.example.capstone2.Repository.CoachRepository;
import com.example.capstone2.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassesService {

    private final ClassesRepository classesRepository;
    private final CoachRepository coachRepository;
    private final UserRepository userRepository;

    public List<Classes> getAllClasses(){
        return classesRepository.findAll();
    }

    public void addClasses(Classes classes){

        Coatch c=coachRepository.findCoatchById(classes.getCoachid());


        if(c==null ){

            throw new ApiExeption("coach id not found");
        }

        classesRepository.save(classes);
    }

    public void updateClasses(Integer id,Classes classes){

        Classes c=classesRepository.findClassesById(id);

        if(c==null){
            throw new ApiExeption("wrong id");

        }

        c.setCoachid(classes.getCoachid());
        c.setName(classes.getName());
        c.setType(classes.getType());
        c.setCapacity(classes.getCapacity());
        c.setDate(classes.getDate());
        c.setTime(classes.getTime());

        classesRepository.save(c);
    }


    public void deleteClasses(Integer id){
        Classes c=classesRepository.findClassesById(id);

        if(c==null){
            throw new ApiExeption("wrong id");

        }
        classesRepository.delete(c);

    }
    public List<Classes> getFullyBooked(){

    
        return classesRepository.findClassesByCapacity();
    }

    public List<Classes> getAvailableClasses(){
        

        return classesRepository.findClassesByCapacityGreaterThanEqual();
    }



    public List<Classes> getUpcomingClasses(){
          LocalDate c=LocalDate.now();

        return classesRepository.findClassesByDateAfter(c);
    }

    public List<Classes> getClassesStartAt(Time time){

        if(classesRepository.findClassesByTimeEquals(time).isEmpty()){
            throw new ApiExeption("no classes found");
        }

        return classesRepository.findClassesByTimeEquals(time);
    }



    public List<Classes> getClassesbycoachname(String cname){
        return classesRepository.findCoatchByNames(cname);
    }




}
