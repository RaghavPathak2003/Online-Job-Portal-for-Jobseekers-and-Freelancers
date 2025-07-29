package com.kodvix.OnlineJobPortal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class JobProjectPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //common
    private String mandatorySkills;
    private String qualifications;
    private String title;
    private String description;
    private String postFor;
    private LocalDate postedDate;
    private String experienceRequired;

    //Jobseeker specific
    private String companyName;
    private Double ctc;
    private String jobLocation;
    private String employmentType;
    private String aboutCompany;
    private String workMode;

    //Freelancer specific
    private String clientName;
    private Double hourlyPayRate;
    private String aboutClient;
    private String projectDuration;

    // Mapped to User (Recruiter)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
