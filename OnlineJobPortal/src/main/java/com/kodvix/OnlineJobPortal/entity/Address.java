package com.kodvix.OnlineJobPortal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // permanent, current
    private String street;
    private String city;
    private String state;
    private String country;
    private String postalCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}