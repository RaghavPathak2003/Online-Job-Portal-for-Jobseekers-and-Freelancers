package com.kodvix.OnlineJobPortal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    //private String level; // Beginner, Intermediate, Advanced
    private int yearsOfExperience;
    private String Proficiency;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
