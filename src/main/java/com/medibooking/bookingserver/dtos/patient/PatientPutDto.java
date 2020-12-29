package com.medibooking.bookingserver.dtos.patient;

import lombok.Data;

@Data
public class PatientPutDto {
    private Long id;
    private int age;
    private String gender;
    private String firstName;
    private String lastName;
    private String email;
}
