package com.kodvix.OnlineJobPortal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String degree;
    private String specialization;
    private String university;
    private LocalDate startDate;
    private LocalDate endDate;
    private double percentage;
    private String institute;
    private String passingYear;

    private String tenthBoard;
    private String tenthSchoolName;
    private String tenthPassingYear;
    private double tenthPercentage;

    private String twelfthBoard;
    private String twelfthSchoolName;
    private String twelfthPassingYear;
    private double twelfthPercentage;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
