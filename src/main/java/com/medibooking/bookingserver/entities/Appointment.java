package com.medibooking.bookingserver.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Setter
@Getter
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Long id;

    @Column(name = "appointment_date")
    private LocalDate date;

    @Column(name = "start_time")
    private LocalTime startingTime;

    @Column(name = "end_time")
    private LocalTime endingTime;

    @Column(name = "notes")
    private String notes;

    @Column(name = "is_cancelled")
    private Boolean isCancelled;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

}
