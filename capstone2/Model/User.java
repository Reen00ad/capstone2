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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "name should not be empty")
    @Size(min = 4,message = "name length must be greater than 4")
    @Column(columnDefinition = "varchar(10) not null ")
    private String name;
    @NotNull(message = "age should not be empty")
    @Min(18)
    @Column(columnDefinition = "int not null")
    private Integer age;
    @NotEmpty(message = "gender should not be empty")
    @Pattern(regexp ="^(male|female)$", message = "gender must be male or female")
    @Column(columnDefinition = "varchar(6) check( gender='male' or gender='female')")
    private String gender;
    @NotEmpty(message = "phone number should not be empty")
    @Size(min = 10,max = 10,message = "phone number must be 10 digit")
    @Column(columnDefinition = "varchar(10) not null ")
    private String phonenumber;
    @Email
    @NotEmpty(message = "email should be not empty")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String email;
    @NotNull(message = "weight should not be empty")
    @Positive
    @Column(columnDefinition = "int not null")
    private Double weight;
    @NotNull(message = "height should not be empty")
    @Positive
    @Column(columnDefinition = "int not null")
    private Double height;
    @NotEmpty(message = "activitylevel should be not empty")
    @Pattern(regexp ="^(sedentary|lightlyactive|moderatelyactive|veryactive|extraactive)$", message = "activitylevel must be sedentary,lightlyactive, moderatelyactive,veryactive, or extraactive only")
    @Column(columnDefinition = "varchar(20) not null ")
    private String activitylevel;
}
