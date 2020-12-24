package com.medibooking.bookingserver.mappers;

import com.medibooking.bookingserver.dtos.specialization.SpecializationGetDto;
import com.medibooking.bookingserver.dtos.specialization.SpecializationPostDto;
import com.medibooking.bookingserver.dtos.specialization.SpecializationPutDto;
import com.medibooking.bookingserver.entities.Specialization;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SpecializationMapper {
    Specialization toEntity(SpecializationPostDto spePostDto);

    SpecializationGetDto fromEntity(Specialization spe);

    void copy(SpecializationPutDto spePutDto, @MappingTarget Specialization spe);
}
