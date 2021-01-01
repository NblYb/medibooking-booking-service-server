package com.medibooking.bookingserver.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medibooking.bookingserver.dtos.patient.PatientGetDto;
import com.medibooking.bookingserver.dtos.patient.PatientPostDto;
import com.medibooking.bookingserver.dtos.patient.PatientPutDto;
import com.medibooking.bookingserver.services.PatientService;
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
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PatientController.class)
@Import(PatientController.class)
@ContextConfiguration(classes = {Utilities.class})
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Utilities utilities;

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void ShouldReturnPatientListWhenGetAllPatients() throws Exception {
        PatientGetDto patientGetDto = utilities.buildPatientGetDto(1L,
                25,
                "male",
                "test",
                "test",
                "test",
                2L
        );
        BDDMockito.given(patientService.getAllPatients()).willReturn(List.of(patientGetDto));
        this.mockMvc.perform(get("/patients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]").exists())
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[0].age").value(25))
                .andExpect(jsonPath("$.[0].gender").value("male"))
                .andExpect(jsonPath("$.[0].firstName").value("test"))
                .andExpect(jsonPath("$.[0].lastName").value("test"))
                .andExpect(jsonPath("$.[0].email").value("test"))
                .andExpect(jsonPath("$.[0].accountId").value(2L));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void ShouldReturnPatientGivenValidPatientId() throws Exception {
        PatientGetDto patientGetDto = utilities.buildPatientGetDto(1L,
                25,
                "male",
                "test",
                "test",
                "test",
                2L
        );
        BDDMockito.given(patientService.findPatientById(1L)).willReturn(patientGetDto);
        this.mockMvc.perform(get("/patients/patientId/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.age").value(25))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.firstName").value("test"))
                .andExpect(jsonPath("$.lastName").value("test"))
                .andExpect(jsonPath("$.email").value("test"))
                .andExpect(jsonPath("$.accountId").value(2L));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void ShouldReturnPatientGivenValidAccountId() throws Exception {
        PatientGetDto patientGetDto = utilities.buildPatientGetDto(1L,
                25,
                "male",
                "test",
                "test",
                "test",
                2L
        );
        BDDMockito.given(patientService.findPatientByAccountId(1L)).willReturn(patientGetDto);
        this.mockMvc.perform(get("/patients/search").param("accountId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.age").value(25))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.firstName").value("test"))
                .andExpect(jsonPath("$.lastName").value("test"))
                .andExpect(jsonPath("$.email").value("test"))
                .andExpect(jsonPath("$.accountId").value(2L));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void ShouldReturnPatientGivenValidPatientName() throws Exception {
        PatientGetDto patientGetDto = utilities.buildPatientGetDto(1L,
                25,
                "male",
                "test",
                "test",
                "test",
                2L
        );
        BDDMockito.given(patientService.findPatientByName(patientGetDto.getFirstName())).willReturn(patientGetDto);
        this.mockMvc.perform(get("/patients/search").param("patientName", patientGetDto.getFirstName()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.age").value(25))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.firstName").value("test"))
                .andExpect(jsonPath("$.lastName").value("test"))
                .andExpect(jsonPath("$.email").value("test"))
                .andExpect(jsonPath("$.accountId").value(2L));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void ShouldReturnPatientWhenAddNewPatient() throws Exception {
        PatientPostDto patientPostDto = utilities.buildPatientPostDto(25,
                "male",
                "test",
                "test",
                "test",
                2L
        );
        PatientGetDto patientGetDto = utilities.buildPatientGetDto(1L,
                25,
                "male",
                "test",
                "test",
                "test",
                2L
        );
        BDDMockito.given(patientService.create(patientPostDto)).willReturn(patientGetDto);
        this.mockMvc.perform(post("/patients").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patientPostDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.age").value(25))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.firstName").value("test"))
                .andExpect(jsonPath("$.lastName").value("test"))
                .andExpect(jsonPath("$.email").value("test"))
                .andExpect(jsonPath("$.accountId").value(2L));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void ShouldReturnPatientWhenUpdatePatient() throws Exception {
        PatientPutDto patientPutDto = utilities.buildPatientPutDto(1L,
                25,
                "male",
                "test",
                "test",
                "test"
        );
        PatientGetDto patientGetDto = utilities.buildPatientGetDto(1L,
                25,
                "male",
                "test",
                "test",
                "test",
                2L
        );
        BDDMockito.given(patientService.modify(1L, patientPutDto)).willReturn(patientGetDto);
        this.mockMvc.perform(put("/patients/1").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patientPutDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.age").value(25))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.firstName").value("test"))
                .andExpect(jsonPath("$.lastName").value("test"))
                .andExpect(jsonPath("$.email").value("test"))
                .andExpect(jsonPath("$.accountId").value(2L));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void ShouldReturnOKWhenDeletePatient() throws Exception {
        BDDMockito.doNothing().when(patientService).delete(1L);
        this.mockMvc.perform(delete("/patients/1").with(csrf())).andExpect(status().isOk());
    }

}