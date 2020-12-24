package com.medibooking.bookingserver.repositories;

import com.medibooking.bookingserver.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface
PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByFirstName(String firstName);

    @Query("select p from Patient p where p.accountId = :account")
    Patient findByAccountId(@Param("account") Long account);
}
