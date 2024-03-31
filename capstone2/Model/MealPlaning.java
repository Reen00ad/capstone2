package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class MealPlaning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "userid should not be empty")
    @Column(columnDefinition = "int not null ")
    private Integer userid;
    @NotNull(message = "foodid should not be empty")
    @Column(columnDefinition = "int not null ")
    private Integer foodid;






}
