package com.example.sanitasapp.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Doctor {
    @Id
    @SequenceGenerator(
            name = "doctor_sequence",
            sequenceName = "doctor_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "doctor_sequence"
    )
    private Long doctorId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String dateOfBirth;
    private String state;
    private String country;
    private String gender;
    private String address;
    private String phone;
    private String Specialization;
    private String Biography;
    private String Status;

}
