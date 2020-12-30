package com.medibooking.bookingserver.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medibooking.bookingserver.dtos.language.LanguageGetDto;
import com.medibooking.bookingserver.services.LanguageService;
import com.medibooking.bookingserver.utils.Utilities;
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
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LanguageController.class)
@Import(LanguageController.class)
@ContextConfiguration(classes = {Utilities.class})
public class LanguageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LanguageService languageService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Utilities utilities;

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void ShouldReturnLanguageListWhenGetAllLanguages() throws Exception {
        LanguageGetDto languageGetDto = utilities.buildLanguageGetDto(1L,
                "test");
        BDDMockito.given(languageService.getAllLanguages()).willReturn(List.of(languageGetDto));
        this.mockMvc.perform(get("/languages"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]").exists())
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[0].languageName").value("test"));
    }
}