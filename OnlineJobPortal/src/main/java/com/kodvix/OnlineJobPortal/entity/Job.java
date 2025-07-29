package com.kodvix.OnlineJobPortal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "jobs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String location;

    private String jobType; // Full-time, Part-time, Internship, Freelance

    private String industry;

    private String experienceLevel; // Entry, Mid, Senior

    private String salaryRange; // Optional: "5-8 LPA"

    private String requiredSkills; // Optional comma-separated: "Java, Spring Boot, SQL"

    private Integer openings; // No. of vacancies

    private LocalDate postedDate;

    private LocalDate lastDateToApply;

    private boolean isActive;

    // Mapped to User (Recruiter)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
