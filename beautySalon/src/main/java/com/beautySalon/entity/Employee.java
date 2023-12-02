package com.beautySalon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String telNo;
    private String picURL;
    private String massage;
    private Double wallet;

    @ManyToMany
    private List<Stock> myStock;
    @ManyToMany
    private List<Appointment> appointmentList;
}
