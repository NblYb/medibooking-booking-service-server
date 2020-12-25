package com.medibooking.bookingserver.dtos.patient;

import lombok.Data;

@Data
public class PatientGetDto {
    private Long id;
    private int age;
    private String gender;
    private String firstName;
    private String lastName;
    private Long accountId;
}
