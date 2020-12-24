package com.medibooking.bookingserver.repositories;

import com.medibooking.bookingserver.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query("select d from Doctor d where d.accountId = :account")
    Doctor findByAccountId(@Param("account") Long account);
}
