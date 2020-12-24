package com.medibooking.bookingserver.services;

import com.medibooking.bookingserver.dtos.specialization.SpecializationGetDto;
import com.medibooking.bookingserver.dtos.specialization.SpecializationPostDto;
import com.medibooking.bookingserver.dtos.specialization.SpecializationPutDto;
import com.medibooking.bookingserver.entities.Specialization;
import com.medibooking.bookingserver.mappers.SpecializationMapper;
import com.medibooking.bookingserver.repositories.SpecializationRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpecializationService {
    @Autowired
    private final SpecializationRepository speRepository;
    private final SpecializationMapper speMapper;

    public SpecializationGetDto createSpe(SpecializationPostDto spePostDto) {
        Specialization speEntity = speMapper.toEntity(spePostDto);

        return speMapper.fromEntity(speRepository.save(speEntity));

    }

    public SpecializationGetDto modify(Long speId, SpecializationPutDto spePutDto) {
        Specialization spe = new Specialization();
        speMapper.copy(spePutDto, spe);
        spe.setId(speId);
        return speMapper.fromEntity(speRepository.save(spe));
    }

    public List<SpecializationGetDto> getAllSpe() {
        return speRepository.findAll().stream()
                .map(speMapper::fromEntity)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        speRepository.deleteById(id);
    }
}
