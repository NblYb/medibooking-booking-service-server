package com.medibooking.bookingserver.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "specializations")
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "specialization_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "specialization_name", nullable = false)
    private String specializationName;

    @ManyToMany
    @JoinTable(name = "doctors_specializations",
            joinColumns = @JoinColumn(name = "specialization_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id"))
    private Set<Doctor> doctors;
}
