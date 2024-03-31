package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "name should not be empty")
    @Column(columnDefinition = "varchar(15) not null ")
    private String name;
    @NotEmpty(message = "description should not be empty")
    @Column(columnDefinition = "varchar(256) not null ")
    private String description;
    @NotNull(message = "calories should not be empty")
    @Column(columnDefinition = "int not null ")
    private Double calories;



}
