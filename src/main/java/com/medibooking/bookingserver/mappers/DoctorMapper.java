package com.medibooking.bookingserver.mappers;

import com.medibooking.bookingserver.dtos.doctor.DoctorGetDto;
import com.medibooking.bookingserver.dtos.doctor.DoctorPostDto;
import com.medibooking.bookingserver.dtos.doctor.DoctorPutDto;
import com.medibooking.bookingserver.entities.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DoctorMapper {
    Doctor toEntity(DoctorPostDto doctorPostDto);

    DoctorGetDto fromEntity(Doctor doctor);

    void copy(DoctorPutDto doctorPutDto, @MappingTarget Doctor doctor);
}
