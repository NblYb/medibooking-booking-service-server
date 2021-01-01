package com.medibooking.bookingserver.services;

import com.medibooking.bookingserver.dtos.specialization.SpecializationGetDto;
import com.medibooking.bookingserver.entities.Specialization;
import com.medibooking.bookingserver.mappers.*;
import com.medibooking.bookingserver.repositories.SpecializationRepository;
import com.medibooking.bookingserver.utils.Utilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {AppointmentMapperImpl.class, DoctorMapperImpl.class,
        LanguageMapperImpl.class, PatientMapperImpl.class, SpecializationMapperImpl.class, Utilities.class})
public class SpecializationServiceTest {

    @Mock
    private SpecializationRepository specializationRepository;

    @Autowired
    private SpecializationMapper specializationMapper;

    @Autowired
    private Utilities utility;

    SpecializationService specializationService;

    @BeforeEach
    void setup() {
        specializationService = new SpecializationService(specializationRepository, specializationMapper);
    }

    @Test
    public void shouldReturnAccountListGivenAccountsExist() {
        Specialization specialization1 = utility.buildSpecialization(666L,"medicine");
        Specialization specialization2 = utility.buildSpecialization(777L,"pregnancy");
        when(specializationRepository.findAll()).thenReturn(List.of(specialization1, specialization2));
        List<SpecializationGetDto> returnedSpecializationList = specializationService.getAllSpe();
        assertNotNull(returnedSpecializationList);
        assertEquals(2, returnedSpecializationList.size());
    }

    @Test
    public void NumberOfSpecializationsShouldBeLessAfterDeletion() {
        specializationService.delete(306L);
        verify(specializationRepository).deleteById(306L);
    }

}
