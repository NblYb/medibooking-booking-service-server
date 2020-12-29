package com.medibooking.messages.email;

import lombok.Data;
import lombok.RequiredArgsConstructor;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@RequiredArgsConstructor
public class Email implements Serializable {

    private String email;
    private LocalDate date;
    private LocalTime startingTime;
    private String notes;
    private String patientFirstName;
    private String patientLastName;
    private String doctorFirstName;
    private String doctorLastName;

}
