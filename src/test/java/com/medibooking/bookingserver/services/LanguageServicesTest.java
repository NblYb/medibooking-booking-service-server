package com.medibooking.bookingserver.services;

import com.medibooking.bookingserver.dtos.language.LanguageGetDto;
import com.medibooking.bookingserver.entities.Language;
import com.medibooking.bookingserver.mappers.*;
import com.medibooking.bookingserver.repositories.LanguageRepository;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {AppointmentMapperImpl.class, DoctorMapperImpl.class,
        LanguageMapperImpl.class, PatientMapperImpl.class, SpecializationMapperImpl.class, Utilities.class})
public class LanguageServicesTest {

    @Mock
    private LanguageRepository languageRepository;

    @Autowired
    private LanguageMapper languageMapper;

    @Autowired
    private Utilities utility;

    LanguageService languageService;

    @BeforeEach
    void setup() {
        languageService = new LanguageService(languageRepository, languageMapper);
    }

    @Test
    public void shouldReturnLanguageListGivenLanguagesExist() {
        Language language1 = utility.buildLanguage("English");
        Language language2 = utility.buildLanguage("Chinese");
        when(languageRepository.findAll()).thenReturn(List.of(language1, language2));
        List<LanguageGetDto> returnedLanguageList = languageService.getAllLanguages();
        assertNotNull(returnedLanguageList);
        assertEquals(2, returnedLanguageList.size());
    }
}
