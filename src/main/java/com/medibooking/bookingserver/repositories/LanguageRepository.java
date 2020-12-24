package com.medibooking.bookingserver.repositories;

import com.medibooking.bookingserver.entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {
}
