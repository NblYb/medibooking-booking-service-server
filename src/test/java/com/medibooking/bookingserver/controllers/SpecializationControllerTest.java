package com.medibooking.bookingserver.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medibooking.bookingserver.dtos.specialization.SpecializationGetDto;
import com.medibooking.bookingserver.services.SpecializationService;
import com.medibooking.bookingserver.utils.Utilities;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SpecializationController.class)
@Import(SpecializationController.class)
@ContextConfiguration(classes = {Utilities.class})
public class SpecializationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpecializationService specializationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private Utilities utilities;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void ShouldReturnSpecializationListWhenGetAllSpecializations() throws Exception {
        SpecializationGetDto specializationGetDto = utilities.buildSpecializationGetDto(1L,
                "test");
        BDDMockito.given(specializationService.getAllSpe()).willReturn(List.of(specializationGetDto));
        this.mockMvc.perform(get("/specializations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]").exists())
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[0].specializationName").value("test"));
    }
}