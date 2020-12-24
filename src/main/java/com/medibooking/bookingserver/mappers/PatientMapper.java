package com.medibooking.bookingserver.mappers;

import com.medibooking.bookingserver.dtos.patient.PatientGetDto;
import com.medibooking.bookingserver.dtos.patient.PatientPostDto;
import com.medibooking.bookingserver.dtos.patient.PatientPutDto;
import com.medibooking.bookingserver.entities.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatientMapper {
    Patient toEntity(PatientPostDto patientPostDto);

    PatientGetDto fromEntity(Patient patient);

    void copy(PatientPutDto patientPutDto, @MappingTarget Patient patient);
}
