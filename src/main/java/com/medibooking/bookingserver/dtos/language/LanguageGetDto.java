package com.medibooking.bookingserver.dtos.language;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LanguageGetDto {
    private Long id;
    private String languageName;
}
