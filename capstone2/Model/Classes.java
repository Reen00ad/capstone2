package com.example.capstone2.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Timer;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Classes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "coachid should not be empty")
    @Column(columnDefinition = "int not null ")
    private Integer coachid;
    @NotEmpty(message = "name should not be empty")
    @Column(columnDefinition = "varchar(20) not null ")
    private String name;
    @NotEmpty(message = "type should not be empty")
    @Column(columnDefinition = "varchar(20) not null ")
    private String type;
    @NotNull(message = "capacity should not be empty")
    @Max(value = 20,message = "max number of capacity is 20")
    @Column(columnDefinition = "int not null ")
    private Integer capacity;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @JsonFormat(pattern = "HH:mm:ss")
    private Time time;


}
