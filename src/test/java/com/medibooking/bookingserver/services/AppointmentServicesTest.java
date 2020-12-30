package com.medibooking.bookingserver.services;

import com.medibooking.bookingserver.dtos.appointment.AppointmentGetDto;
import com.medibooking.bookingserver.entities.Appointment;
import com.medibooking.bookingserver.entities.Doctor;
import com.medibooking.bookingserver.entities.Patient;
import com.medibooking.bookingserver.mappers.*;
import com.medibooking.bookingserver.repositories.AppointmentRepository;
import com.medibooking.bookingserver.repositories.DoctorRepository;
import com.medibooking.bookingserver.repositories.PatientRepository;
import com.medibooking.bookingserver.utils.Utilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = { AppointmentMapperImpl.class, DoctorMapperImpl.class,
        LanguageMapperImpl.class, PatientMapperImpl.class, SpecializationMapperImpl.class, Utilities.class})
public class AppointmentServicesTest {
    @Mock
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Autowired(required = false)
    private PatientRepository patientRepository;

    @Autowired(required = false)
    private DoctorRepository doctorRepository;

    @Autowired
    private Utilities utility;

    AppointmentService appointmentService;

    @BeforeEach
    void setup() {
        appointmentService = new AppointmentService(appointmentRepository, appointmentMapper,
                patientRepository,doctorRepository);
    }

    @Test
    public void shouldReturnAppointmentListGivenAppointmentsExist() {

        Appointment appointment1 = utility.buildAppointment(34L,LocalDate.now() , LocalTime.now(),
                LocalTime.now(),"aa",false);
        Appointment appointment2 = utility.buildAppointment(345L,LocalDate.now() , LocalTime.now(),
                LocalTime.now(),"aa",false);

        when(appointmentRepository.findAll()).thenReturn(List.of(appointment1, appointment2));
        List<AppointmentGetDto> returnedAppointmentList = appointmentService.getAll();
        assertNotNull(returnedAppointmentList);
        assertEquals(2, returnedAppointmentList.size());
    }

    @Test
    public void NumberOfAppointmentsShouldBeLessAfterDeletion() {

        appointmentService.deleteAppointment(306L);
        verify(appointmentRepository).deleteById(306L);
    }

    @Test
    public void shouldReturnAppointmentGivenId() {
        Appointment appointment1 = utility.buildAppointment(34L,LocalDate.now() , LocalTime.now(),
                LocalTime.now(),"aa",false);
        when(appointmentRepository.getOne(34L)).thenReturn(appointment1);
        AppointmentGetDto returnedAppointment = appointmentService.findAppointmentById(34L);
        assertNotNull(returnedAppointment);
        assertEquals(appointment1.getId(), returnedAppointment.getId());
    }

    @Test
    public void shouldReturnAppointmentGivenPatient() {
        Appointment appointment1 = utility.buildAppointment(34L,LocalDate.now() , LocalTime.now(),
                LocalTime.now(),"aa",false);

        Patient patient1 = utility.buildPatientWithId(30L, 35, "male","Jack","io");
        patient1.setAppointments(Set.of(appointment1));

        when(appointmentRepository.findAppointmentsByPatientId(30L)).thenReturn(List.of(appointment1));
        List<AppointmentGetDto> returnedAppointment = appointmentService.findAppointmentsByPatient(30L);
        assertNotNull(returnedAppointment);
        assertEquals(appointment1.getId(),returnedAppointment.get(0).getId());
    }

    @Test
    public void shouldReturnAppointmentGivenDoctor() {
        Appointment appointment1 = utility.buildAppointment(34L,LocalDate.now() , LocalTime.now(),
                LocalTime.now(),"aa",false);

        Doctor doctor1 = utility.buildDoctorWithId(30L, 35, "male","Jack","io");
        doctor1.setAppointments(Set.of(appointment1));

        when(appointmentRepository.findAppointmentsByDoctorId(30L)).thenReturn(List.of(appointment1));
        List<AppointmentGetDto> returnedAppointment = appointmentService.findAppointmentsByDoctor(30L);
        assertNotNull(returnedAppointment);
        assertEquals(appointment1.getId(),returnedAppointment.get(0).getId());
    }

    @Test
    public void shouldReturnAppointmentGivenDoctorAndDate() {
        LocalDate localDate1=LocalDate.of(2020,5,12);
        LocalDate localDate2=LocalDate.of(2020,7,12);
        LocalTime localTime1=LocalTime.of(14,25);
        LocalTime localTime2=LocalTime.of(14,25);
        LocalTime localTime3=LocalTime.of(14,25);
        LocalTime localTime4=LocalTime.of(14,25);

        Appointment appointment1 = utility.buildAppointment(34L,localDate1, localTime1,
                localTime2,"aa",false);
        Appointment appointment2 = utility.buildAppointment(35L,localDate2, localTime3,
                localTime4,"aa",false);

        Doctor doctor1 = utility.buildDoctorWithId(30L, 35, "male","Jack","io");

        doctor1.setAppointments(Set.of(appointment1));
        doctor1.setAppointments(Set.of(appointment2));

        when(appointmentRepository.findAppointmentsOfADoctorByDate(30L, localDate1)).
                thenReturn(List.of(appointment1));
        List<AppointmentGetDto> returnedAppointment = appointmentService.
                findAppointmentsOfADoctorByDate(30L, localDate1);
        assertNotNull(returnedAppointment);
        assertEquals(appointment1.getId(),returnedAppointment.get(0).getId());
    }

    @Test
    public void shouldReturnAppointmentGivenPatientAndDate() {
        LocalDate localDate1=LocalDate.of(2020,5,12);
        LocalDate localDate2=LocalDate.of(2020,7,12);

        Appointment appointment1 = utility.buildAppointment(34L,localDate1, LocalTime.now(),
                LocalTime.now(),"aa",false);
        Appointment appointment2 = utility.buildAppointment(35L,localDate2, LocalTime.now(),
                LocalTime.now(),"aa",false);

        Patient patient1 = utility.buildPatientWithId(30L, 35, "male","Jack","io");

        patient1.setAppointments(Set.of(appointment1));
        patient1.setAppointments(Set.of(appointment2));

        when(appointmentRepository.findAppointmentsOfAPatientByDate(30L, localDate1)).
                thenReturn(List.of(appointment1));
        List<AppointmentGetDto> returnedAppointment = appointmentService.
                findAppointmentsOfAPatientByDate(30L, localDate1);
        assertNotNull(returnedAppointment);
        assertEquals(appointment1.getId(),returnedAppointment.get(0).getId());
    }

    @Test
    public void shouldReturnAppointmentGivenDate() {
        LocalDate localDate1=LocalDate.of(2020,5,12);
        LocalDate localDate2=LocalDate.of(2020,6,12);
        LocalDate localDate3=LocalDate.of(2020,7,12);

        Appointment appointment1 = utility.buildAppointment(34L,localDate1 , LocalTime.now(),
                LocalTime.now(),"aa",false);
        Appointment appointment2 = utility.buildAppointment(334L,localDate2 , LocalTime.now(),
                LocalTime.now(),"aa",false);
        Appointment appointment3 = utility.buildAppointment(354L,localDate3 , LocalTime.now(),
                LocalTime.now(),"aa",false);

        when(appointmentRepository.findAppointmentsByDate(localDate1)).thenReturn(List.of(appointment1));
        List<AppointmentGetDto> returnedAppointment = appointmentService.findAppointmentsByDate(localDate1);
        assertNotNull(returnedAppointment);
        assertEquals(appointment1.getId(),returnedAppointment.get(0).getId());
    }
}
