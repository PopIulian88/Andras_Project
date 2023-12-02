package com.beautySalon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String telNo;
    private int day;
    private int month;
    private int year;
    private String service;
    private String massage;

    @Override
    public String toString() {
        return "{" +
                "\"id\": \"" + id + "\"" +
                ", \"firstName\": \"" + firstName + '\"' +
                ", \"lastName\": \"" + lastName + '\"' +
                ", \"email\": \"" + email + '\"' +
                ", \"telNo\": \"" + telNo + '\"' +
                ", \"day\": \"" + day + "\"" +
                ", \"month\": \"" + month + "\"" +
                ", \"year\": \"" + year + "\"" +
                ", \"service\": \"" + service + '\"' +
                ", \"massage\": \"" + massage + '\"' +
                '}';
    }
}
