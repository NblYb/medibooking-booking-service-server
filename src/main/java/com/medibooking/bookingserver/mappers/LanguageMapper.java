package com.medibooking.bookingserver.mappers;

import com.medibooking.bookingserver.dtos.language.LanguageGetDto;
import com.medibooking.bookingserver.dtos.language.LanguagePostDto;
import com.medibooking.bookingserver.dtos.language.LanguagePutDto;
import com.medibooking.bookingserver.entities.Language;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LanguageMapper {
    Language toEntity(LanguagePostDto languagePostDto);

    LanguageGetDto fromEntity(Language language);

    void copy(LanguagePutDto languagePutDto, @MappingTarget Language language);
}
