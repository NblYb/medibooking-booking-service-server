package com.medibooking.bookingserver.repositories;

import com.medibooking.bookingserver.entities.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
}