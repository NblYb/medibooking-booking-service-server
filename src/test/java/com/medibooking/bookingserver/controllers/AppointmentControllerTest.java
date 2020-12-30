package com.medibooking.bookingserver.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medibooking.bookingserver.dtos.appointment.AppointmentGetDto;
import com.medibooking.bookingserver.dtos.appointment.AppointmentPostDto;
import com.medibooking.bookingserver.dtos.appointment.AppointmentPutDto;
import com.medibooking.bookingserver.services.AppointmentService;
import com.medibooking.bookingserver.utils.Utilities;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(AppointmentController.class)
@Import(AppointmentController.class)
@ContextConfiguration(classes = {Utilities.class})
public class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppointmentService appointmentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Utilities utilities;

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void ShouldReturnAppointmentListWhenGetAllAppointments() throws Exception {
        LocalDate testDate = LocalDate.now();
        LocalTime testTime = LocalTime.now();
        AppointmentGetDto appointmentGetDto = utilities.buildAppointmentGetDto(1L,
                testDate,
                testTime,
                testTime,
                "test",
                false,
                2L,
                "testPatient",
                "testPatient",
                3L,
                "testDoctor",
                "testDoctor");
        BDDMockito.given(appointmentService.getAll()).willReturn(List.of(appointmentGetDto));
        this.mockMvc.perform(get("/management/appointments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]").exists())
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[0].date").value(testDate.toString()))
                .andExpect(jsonPath("$.[0].startingTime").value(testTime.toString().substring(0, (testTime.toString().length() - 2))))
                .andExpect(jsonPath("$.[0].endingTime").value(testTime.toString().substring(0, (testTime.toString().length() - 2))))
                .andExpect(jsonPath("$.[0].notes").value("test"))
                .andExpect(jsonPath("$.[0].isCancelled").value(false))
                .andExpect(jsonPath("$.[0].patient").value(2L))
                .andExpect(jsonPath("$.[0].patientFirstName").value("testPatient"))
                .andExpect(jsonPath("$.[0].patientLastName").value("testPatient"))
                .andExpect(jsonPath("$.[0].doctor").value(3L))
                .andExpect(jsonPath("$.[0].doctorFirstName").value("testDoctor"))
                .andExpect(jsonPath("$.[0].doctorLastName").value("testDoctor"));

    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void ShouldReturnAppointmentWhenGivenValidAppointmentId() throws Exception {
        LocalDate testDate = LocalDate.now();
        LocalTime testTime = LocalTime.now();
        AppointmentGetDto appointmentGetDto = utilities.buildAppointmentGetDto(1L,
                testDate,
                testTime,
                testTime,
                "test",
                false,
                2L,
                "testPatient",
                "testPatient",
                3L,
                "testDoctor",
                "testDoctor");
        BDDMockito.given(appointmentService.findAppointmentById(1L)).willReturn(appointmentGetDto);
        this.mockMvc.perform(get("/management/appointments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.date").value(testDate.toString()))
                .andExpect(jsonPath("$.startingTime").value(testTime.toString().substring(0, (testTime.toString().length() - 2))))
                .andExpect(jsonPath("$.endingTime").value(testTime.toString().substring(0, (testTime.toString().length() - 2))))
                .andExpect(jsonPath("$.notes").value("test"))
                .andExpect(jsonPath("$.isCancelled").value(false))
                .andExpect(jsonPath("$.patient").value(2L))
                .andExpect(jsonPath("$.patientFirstName").value("testPatient"))
                .andExpect(jsonPath("$.patientLastName").value("testPatient"))
                .andExpect(jsonPath("$.doctor").value(3L))
                .andExpect(jsonPath("$.doctorFirstName").value("testDoctor"))
                .andExpect(jsonPath("$.doctorLastName").value("testDoctor"));

    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void ShouldReturnAppointmentWhenGivenValidPatientId() throws Exception {
        LocalDate testDate = LocalDate.now();
        LocalTime testTime = LocalTime.now();
        AppointmentGetDto appointmentGetDto = utilities.buildAppointmentGetDto(1L,
                testDate,
                testTime,
                testTime,
                "test",
                false,
                2L,
                "testPatient",
                "testPatient",
                3L,
                "testDoctor",
                "testDoctor");
        BDDMockito.given(appointmentService.findAppointmentsByPatient(2L)).willReturn(List.of(appointmentGetDto));
        this.mockMvc.perform(get("/management/appointments/search").param("patientId", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]").exists())
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[0].date").value(testDate.toString()))
                .andExpect(jsonPath("$.[0].startingTime").value(testTime.toString().substring(0, (testTime.toString().length() - 2))))
                .andExpect(jsonPath("$.[0].endingTime").value(testTime.toString().substring(0, (testTime.toString().length() - 2))))
                .andExpect(jsonPath("$.[0].notes").value("test"))
                .andExpect(jsonPath("$.[0].isCancelled").value(false))
                .andExpect(jsonPath("$.[0].patient").value(2L))
                .andExpect(jsonPath("$.[0].patientFirstName").value("testPatient"))
                .andExpect(jsonPath("$.[0].patientLastName").value("testPatient"))
                .andExpect(jsonPath("$.[0].doctor").value(3L))
                .andExpect(jsonPath("$.[0].doctorFirstName").value("testDoctor"))
                .andExpect(jsonPath("$.[0].doctorLastName").value("testDoctor"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void ShouldReturnAppointmentWhenGivenValidDoctorId() throws Exception {
        LocalDate testDate = LocalDate.now();
        LocalTime testTime = LocalTime.now();
        AppointmentGetDto appointmentGetDto = utilities.buildAppointmentGetDto(1L,
                testDate,
                testTime,
                testTime,
                "test",
                false,
                2L,
                "testPatient",
                "testPatient",
                3L,
                "testDoctor",
                "testDoctor");
        BDDMockito.given(appointmentService.findAppointmentsByDoctor(3L)).willReturn(List.of(appointmentGetDto));
        this.mockMvc.perform(get("/management/appointments/search").param("doctorId", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]").exists())
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[0].date").value(testDate.toString()))
                .andExpect(jsonPath("$.[0].startingTime").value(testTime.toString().substring(0, (testTime.toString().length() - 2))))
                .andExpect(jsonPath("$.[0].endingTime").value(testTime.toString().substring(0, (testTime.toString().length() - 2))))
                .andExpect(jsonPath("$.[0].notes").value("test"))
                .andExpect(jsonPath("$.[0].isCancelled").value(false))
                .andExpect(jsonPath("$.[0].patient").value(2L))
                .andExpect(jsonPath("$.[0].patientFirstName").value("testPatient"))
                .andExpect(jsonPath("$.[0].patientLastName").value("testPatient"))
                .andExpect(jsonPath("$.[0].doctor").value(3L))
                .andExpect(jsonPath("$.[0].doctorFirstName").value("testDoctor"))
                .andExpect(jsonPath("$.[0].doctorLastName").value("testDoctor"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void ShouldReturnAppointmentWhenGivenValidPatientAndDate() throws Exception {
        LocalDate testDate = LocalDate.now();
        LocalTime testTime = LocalTime.now();
        AppointmentGetDto appointmentGetDto = utilities.buildAppointmentGetDto(1L,
                testDate,
                testTime,
                testTime,
                "test",
                false,
                2L,
                "testPatient",
                "testPatient",
                3L,
                "testDoctor",
                "testDoctor");
        BDDMockito.given(appointmentService.findAppointmentsOfAPatientByDate(2L, testDate)).willReturn(List.of(appointmentGetDto));
        this.mockMvc.perform(get("/management/appointments/search").param("patientId", "2").param("date", testDate.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]").exists())
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[0].date").value(testDate.toString()))
                .andExpect(jsonPath("$.[0].startingTime").value(testTime.toString().substring(0, (testTime.toString().length() - 2))))
                .andExpect(jsonPath("$.[0].endingTime").value(testTime.toString().substring(0, (testTime.toString().length() - 2))))
                .andExpect(jsonPath("$.[0].notes").value("test"))
                .andExpect(jsonPath("$.[0].isCancelled").value(false))
                .andExpect(jsonPath("$.[0].patient").value(2L))
                .andExpect(jsonPath("$.[0].patientFirstName").value("testPatient"))
                .andExpect(jsonPath("$.[0].patientLastName").value("testPatient"))
                .andExpect(jsonPath("$.[0].doctor").value(3L))
                .andExpect(jsonPath("$.[0].doctorFirstName").value("testDoctor"))
                .andExpect(jsonPath("$.[0].doctorLastName").value("testDoctor"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void ShouldReturnAppointmentWhenGivenValidDoctorAndDate() throws Exception {
        LocalDate testDate = LocalDate.now();
        LocalTime testTime = LocalTime.now();
        AppointmentGetDto appointmentGetDto = utilities.buildAppointmentGetDto(1L,
                testDate,
                testTime,
                testTime,
                "test",
                false,
                2L,
                "testPatient",
                "testPatient",
                3L,
                "testDoctor",
                "testDoctor");
        BDDMockito.given(appointmentService.findAppointmentsOfADoctorByDate(3L, testDate)).willReturn(List.of(appointmentGetDto));
        this.mockMvc.perform(get("/management/appointments/search").param("doctorId", "3").param("date", testDate.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]").exists())
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[0].date").value(testDate.toString()))
                .andExpect(jsonPath("$.[0].startingTime").value(testTime.toString().substring(0, (testTime.toString().length() - 2))))
                .andExpect(jsonPath("$.[0].endingTime").value(testTime.toString().substring(0, (testTime.toString().length() - 2))))
                .andExpect(jsonPath("$.[0].notes").value("test"))
                .andExpect(jsonPath("$.[0].isCancelled").value(false))
                .andExpect(jsonPath("$.[0].patient").value(2L))
                .andExpect(jsonPath("$.[0].patientFirstName").value("testPatient"))
                .andExpect(jsonPath("$.[0].patientLastName").value("testPatient"))
                .andExpect(jsonPath("$.[0].doctor").value(3L))
                .andExpect(jsonPath("$.[0].doctorFirstName").value("testDoctor"))
                .andExpect(jsonPath("$.[0].doctorLastName").value("testDoctor"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void ShouldReturnAppointmentWhenGivenValidDate() throws Exception {
        LocalDate testDate = LocalDate.now();
        LocalTime testTime = LocalTime.now();
        AppointmentGetDto appointmentGetDto = utilities.buildAppointmentGetDto(1L,
                testDate,
                testTime,
                testTime,
                "test",
                false,
                2L,
                "testPatient",
                "testPatient",
                3L,
                "testDoctor",
                "testDoctor");
        BDDMockito.given(appointmentService.findAppointmentsByDate(testDate)).willReturn(List.of(appointmentGetDto));
        this.mockMvc.perform(get("/management/appointments/search").param("date", testDate.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]").exists())
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[0].date").value(testDate.toString()))
                .andExpect(jsonPath("$.[0].startingTime").value(testTime.toString().substring(0, (testTime.toString().length() - 2))))
                .andExpect(jsonPath("$.[0].endingTime").value(testTime.toString().substring(0, (testTime.toString().length() - 2))))
                .andExpect(jsonPath("$.[0].notes").value("test"))
                .andExpect(jsonPath("$.[0].isCancelled").value(false))
                .andExpect(jsonPath("$.[0].patient").value(2L))
                .andExpect(jsonPath("$.[0].patientFirstName").value("testPatient"))
                .andExpect(jsonPath("$.[0].patientLastName").value("testPatient"))
                .andExpect(jsonPath("$.[0].doctor").value(3L))
                .andExpect(jsonPath("$.[0].doctorFirstName").value("testDoctor"))
                .andExpect(jsonPath("$.[0].doctorLastName").value("testDoctor"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void ShouldReturnAppointmentWhenAddNewAppointment() throws Exception {
        LocalDate testDate = LocalDate.now();
        LocalTime testTime = LocalTime.now();
        AppointmentGetDto appointmentGetDto = utilities.buildAppointmentGetDto(1L,
                testDate,
                testTime,
                testTime,
                "test",
                false,
                2L,
                "testPatient",
                "testPatient",
                3L,
                "testDoctor",
                "testDoctor");
        AppointmentPostDto appointmentPostDto = utilities.buildAppointmentPostDto(testDate,
                testTime,
                testTime,
                "test",
                false,
                2L,
                3L);
        BDDMockito.given(appointmentService.createAppointment(appointmentPostDto)).willReturn(appointmentGetDto);
        this.mockMvc.perform(post("/management/appointments").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(appointmentPostDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.date").value(testDate.toString()))
                .andExpect(jsonPath("$.startingTime").value(testTime.toString().substring(0, (testTime.toString().length() - 2))))
                .andExpect(jsonPath("$.endingTime").value(testTime.toString().substring(0, (testTime.toString().length() - 2))))
                .andExpect(jsonPath("$.notes").value("test"))
                .andExpect(jsonPath("$.isCancelled").value(false))
                .andExpect(jsonPath("$.patient").value(2L))
                .andExpect(jsonPath("$.patientFirstName").value("testPatient"))
                .andExpect(jsonPath("$.patientLastName").value("testPatient"))
                .andExpect(jsonPath("$.doctor").value(3L))
                .andExpect(jsonPath("$.doctorFirstName").value("testDoctor"))
                .andExpect(jsonPath("$.doctorLastName").value("testDoctor"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void ShouldReturnAppointmentWhenUpdateNewAppointment() throws Exception {
        LocalDate testDate = LocalDate.now();
        LocalTime testTime = LocalTime.now();
        AppointmentGetDto appointmentGetDto = utilities.buildAppointmentGetDto(1L,
                testDate,
                testTime,
                testTime,
                "test",
                false,
                2L,
                "testPatient",
                "testPatient",
                3L,
                "testDoctor",
                "testDoctor");
        AppointmentPutDto appointmentPutDto = utilities.buildAppointmentPutDto(1L,
                testDate,
                testTime,
                testTime,
                "test",
                false,
                2L,
                3L);
        BDDMockito.given(appointmentService.cancelAppointment(appointmentPutDto.getId())).willReturn(appointmentGetDto);
        this.mockMvc.perform(put("/management/appointments/1").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.date").value(testDate.toString()))
                .andExpect(jsonPath("$.startingTime").value(testTime.toString().substring(0, (testTime.toString().length() - 2))))
                .andExpect(jsonPath("$.endingTime").value(testTime.toString().substring(0, (testTime.toString().length() - 2))))
                .andExpect(jsonPath("$.notes").value("test"))
                .andExpect(jsonPath("$.isCancelled").value(false))
                .andExpect(jsonPath("$.patient").value(2L))
                .andExpect(jsonPath("$.patientFirstName").value("testPatient"))
                .andExpect(jsonPath("$.patientLastName").value("testPatient"))
                .andExpect(jsonPath("$.doctor").value(3L))
                .andExpect(jsonPath("$.doctorFirstName").value("testDoctor"))
                .andExpect(jsonPath("$.doctorLastName").value("testDoctor"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void ShouldReturnOKWhenDeleteAppointment() throws Exception {
        BDDMockito.doNothing().when(appointmentService).deleteAppointment(1L);
        this.mockMvc.perform(delete("/management/appointments/1").with(csrf())).andExpect(status().isOk());

    }
}
