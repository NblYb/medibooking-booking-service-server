package com.medibooking.bookingserver.services;

import com.medibooking.bookingserver.dtos.language.LanguageGetDto;
import com.medibooking.bookingserver.dtos.language.LanguagePostDto;
import com.medibooking.bookingserver.entities.Language;
import com.medibooking.bookingserver.mappers.LanguageMapper;
import com.medibooking.bookingserver.repositories.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LanguageService {
    private final LanguageRepository languageRepository;
    private final LanguageMapper languageMapper;

    public List<LanguageGetDto> getAllLanguages() {
        return languageRepository.findAll().stream()
                .map(languageMapper::fromEntity)
                .collect(Collectors.toList());
    }

    public LanguageGetDto createLanguage(LanguagePostDto languagePostDto) {
        Language language = languageMapper.toEntity(languagePostDto);
        languageRepository.save(language);

        return languageMapper.fromEntity(language);
    }
}
