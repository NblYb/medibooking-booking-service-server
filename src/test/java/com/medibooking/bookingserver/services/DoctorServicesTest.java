package com.medibooking.bookingserver.services;

import com.medibooking.bookingserver.dtos.doctor.DoctorGetDto;
import com.medibooking.bookingserver.entities.Doctor;
import com.medibooking.bookingserver.mappers.*;
import com.medibooking.bookingserver.repositories.DoctorRepository;
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
@SpringBootTest(classes = { AppointmentMapperImpl.class, DoctorMapperImpl.class,
        LanguageMapperImpl.class, PatientMapperImpl.class, SpecializationMapperImpl.class, Utilities.class})
public class DoctorServicesTest {

    @Mock
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorMapper doctorMapper;

    @Autowired
    private Utilities utility;

    DoctorService doctorService;

    @BeforeEach
    void setup() {
        doctorService = new DoctorService(doctorRepository, doctorMapper);
    }

    @Test
    public void shouldReturnDoctorListGivenDoctorsExist() {
        Doctor doctor1 = utility.buildDoctor(35,"male","Mini","M");
        Doctor doctor2 = utility.buildDoctor(35,"female","Rita","Jo");

        when(doctorRepository.findAll()).thenReturn(List.of(doctor1, doctor2));
        List<DoctorGetDto> returnedDoctorList = doctorService.getAllDoctors();
        assertNotNull(returnedDoctorList);
        assertEquals(2, returnedDoctorList.size());
    }

//    @Test
//    public void shouldThrowExceptionGivenInvalidAccount() {
//        when(doctorRepository.getOne(any())).thenReturn(Optional.empty());
//        assertThrows(InvalidAccountException.class,() -> doctorService.findDoctorById(5555L));
//    }

    @Test
    public void NumberOfDoctorsShouldBeLessAfterDeletion() {
        doctorService.delete(306L);
        verify(doctorRepository).deleteById(306L);
    }
}
