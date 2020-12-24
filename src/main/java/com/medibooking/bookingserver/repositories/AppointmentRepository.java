package com.medibooking.bookingserver.repositories;

import com.medibooking.bookingserver.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("select a from Appointment a where a.patient.id = :patientId")
    List<Appointment> findAppointmentsByPatientId(@Param("patientId") Long patientId);

    @Query("select a from Appointment a where a.doctor.id = :doctorId")
    List<Appointment> findAppointmentsByDoctorId(@Param("doctorId") Long doctorId);

    @Query("select a from Appointment a where a.patient.id = :patientId and a.date= :date")
    List<Appointment> findAppointmentsOfAPatientByDate(@Param("patientId") Long patientId, @Param("date")LocalDate date);

    @Query("select a from Appointment a where a.doctor.id = :doctorId and a.date= :date")
    List<Appointment> findAppointmentsOfADoctorByDate(@Param("doctorId") Long doctorId, @Param("date")LocalDate date);

    @Query("select a from Appointment a where a.date= :date")
    List<Appointment> findAppointmentsByDate(@Param("date") LocalDate date);
}
