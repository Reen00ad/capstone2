package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Coatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "name should not be empty")
    @Size(min = 4,message = "name length must be greater than 4")
    @Column(columnDefinition = "varchar(15) not null ")
    private String name;
    @NotEmpty(message = "phone number should not be empty")
    @Size(min = 10,max = 10,message = "phone number must be 10 digit")
    @Column(columnDefinition = "varchar(10) not null ")
    private String phonenumber;
    @NotEmpty(message = "specialization should not be empty")
    @Column(columnDefinition = "varchar(15) not null ")
    private String specialization;
    @NotNull(message = "gym id should not be empty")
    @Column(columnDefinition = "int not null ")
    private Integer gymid;

}
