package com.kodvix.OnlineJobPortal.entity;

import com.kodvix.OnlineJobPortal.enums.Gender;
import com.kodvix.OnlineJobPortal.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;


    @Column(unique = true)
    private String email;

    private String password;

    @Column(unique = true)
    private String phone;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate dob;
    @Lob
    @Column(name="profile_image", columnDefinition = "LONGBLOB")
    private byte[] profileImage;

    @Lob
    @Column(name = "resume", columnDefinition = "LONGBLOB")
    private byte[] resume;



    @Enumerated(EnumType.STRING)
    private Role role;




    private LocalDate createdAt;
    private LocalDate updatedAt;




    // 🔽 JobSeeker/Freelancer Specific Fields

    private String portfolioLink;
    private String currentCtc;
    private String expectedCtc;
    private String profileSummary;
    private String noticePeriod;
//    private String certifications;
    private String preferredLocation;
    private String preferredDomain;

    private String expectedHourlyPayRate;


    // Relationships
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Address> addresses;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Skill> skills;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Experience> experiences;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Education> educations;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Company company;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Job> jobs;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Project> projects;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Language> languages;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Certification> certifications;

}