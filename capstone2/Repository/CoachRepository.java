package com.example.capstone2.Repository;

import com.example.capstone2.Model.Coatch;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachRepository extends JpaRepository<Coatch,Integer> {

    Coatch findCoatchById(Integer id);

    @Query("select c from Coatch c where not exists (select 1 from Classes cl where cl.coachid=c.id )")
    List<Coatch> findCoatchWithoutClasses();

    List<Coatch> findCoatchByGymidOrderBySpecializationAsc(Integer gymid);
}
