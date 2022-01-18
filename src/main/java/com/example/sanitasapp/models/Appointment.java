package com.example.sanitasapp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Appointment {
    @Id
    @SequenceGenerator(
            name = "appointment_sequence",
            sequenceName = "appointment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "appointment_sequence"
    )
    private Long appointmentId;
    private String patientName;
    private String department;
    private String patientEmail;
    private String phone;
    private String status;
    private String timeOfMeeting;
    private String dateOfAppointment;
    private String message;

    @CreationTimestamp
    private LocalDateTime timeStamp;


    @ManyToOne(
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "doctor_id",
            referencedColumnName = "doctorId"
    )
    private Doctor doctor;

    @ManyToOne(
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "patient_id",
            referencedColumnName = "patientId"
    )
    private Patient patient;

}
