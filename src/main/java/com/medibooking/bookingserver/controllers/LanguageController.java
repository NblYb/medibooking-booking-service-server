package com.medibooking.bookingserver.controllers;

import com.medibooking.bookingserver.dtos.language.LanguageGetDto;
import com.medibooking.bookingserver.dtos.language.LanguagePostDto;
import com.medibooking.bookingserver.services.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/languages")
@RequiredArgsConstructor
public class LanguageController {
    private final LanguageService languageService;

    @GetMapping
    public ResponseEntity<List<LanguageGetDto>> getAllLanguages() {
        return ResponseEntity.ok(languageService.getAllLanguages());
    }

    @PostMapping
    public ResponseEntity<LanguageGetDto> createLanguage(@RequestBody LanguagePostDto languagePostDto) {
        LanguageGetDto languageGetDto = languageService.createLanguage(languagePostDto);
        return ResponseEntity.ok(languageGetDto);
    }
}
