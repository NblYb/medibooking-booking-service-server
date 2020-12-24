package com.medibooking.bookingserver.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "languages")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Long id;

    @Column(name = "language_name")
    private String languageName;

    @ManyToMany
    @JoinTable(name = "doctors_languages",
            joinColumns = @JoinColumn(name = "language_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id"))
    private Set<Doctor> doctors;
}
