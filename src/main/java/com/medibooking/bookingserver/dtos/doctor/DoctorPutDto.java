package com.medibooking.bookingserver.dtos.doctor;

import com.medibooking.bookingserver.dtos.language.LanguageGetDto;
import com.medibooking.bookingserver.dtos.specialization.SpecializationGetDto;
import lombok.Data;

import java.util.Set;

@Data
public class DoctorPutDto {
    private Long id;
    private int age;
    private String gender;
    private String firstName;
    private String lastName;
    private String description;
    private Set<LanguageGetDto> languages;
    private Set<SpecializationGetDto> specializations;
}