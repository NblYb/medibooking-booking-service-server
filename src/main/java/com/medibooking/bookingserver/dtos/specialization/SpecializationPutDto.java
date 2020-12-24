package com.medibooking.bookingserver.dtos.specialization;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SpecializationPutDto {
    private Long id;
    private String specializationName;
}
