package com.medibooking.bookingserver.services;

import com.medibooking.bookingserver.dtos.patient.PatientGetDto;
import com.medibooking.bookingserver.entities.Patient;
import com.medibooking.bookingserver.mappers.*;
import com.medibooking.bookingserver.repositories.PatientRepository;
import com.medibooking.bookingserver.utils.Utilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {AppointmentMapperImpl.class, DoctorMapperImpl.class,
        LanguageMapperImpl.class, PatientMapperImpl.class, SpecializationMapperImpl.class, Utilities.class})
public class PatientServicesTest {

    @Mock
    private PatientRepository patientRepository;

    @Autowired
    private PatientMapper patientMapper;

    @Autowired
    private Utilities utility;

    PatientService patientService;

    @BeforeEach
    void setup() {
        patientService = new PatientService(patientRepository, patientMapper);
    }

    @Test
    public void shouldReturnPatientGivenPatientId() {
        Patient patient1 = utility.buildPatientWithId(30L, 35, "male","Jack","io");
        when(patientRepository.getOne(30L)).thenReturn(patient1);
        PatientGetDto returnedPatient = patientService.findPatientById(30L);
        assertNotNull(returnedPatient);
        assertEquals(patient1.getId(),returnedPatient.getId());
    }

    @Test
    public void shouldReturnPatientGivenPatientName() {
        Patient patient1 = utility.buildPatientWithId(30L, 35, "male","Jack","io");
        when(patientRepository.findByFirstName("Jack")).thenReturn(patient1);
        PatientGetDto returnedPatient = patientService.findPatientByName("Jack");
        assertNotNull(returnedPatient);
        assertEquals(patient1.getId(),returnedPatient.getId());
    }

    @Test
    public void NumberOfPatientsShouldBeLessAfterDeletion() {
        patientService.delete(306L);
        verify(patientRepository).deleteById(306L);
    }
}
