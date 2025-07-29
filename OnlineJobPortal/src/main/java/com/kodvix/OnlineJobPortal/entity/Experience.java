package com.kodvix.OnlineJobPortal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobTitle;
    private String companyName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private String position;
    private String ctc;
    private String noticePeriod;
    private String location;
    private String employmentType;
    private String yearsOfExperience;
    private String industry;
    private String department;
    private String skills;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
