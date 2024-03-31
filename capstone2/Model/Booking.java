package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "userid should not be empty")
    @Column(columnDefinition = "int not null ")
    private Integer userid;
    @NotNull(message = "classesid should not be empty")
    @Column(columnDefinition = "int not null ")
    private Integer classesid;
    private LocalDate bookingtime;
}
