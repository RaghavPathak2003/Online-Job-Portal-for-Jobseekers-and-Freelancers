package com.kodvix.OnlineJobPortal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String designation;
    private String website;
    private String description;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
