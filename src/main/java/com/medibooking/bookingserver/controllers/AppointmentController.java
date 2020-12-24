package com.medibooking.bookingserver.controllers;

import com.medibooking.bookingserver.dtos.appointment.AppointmentGetDto;
import com.medibooking.bookingserver.dtos.appointment.AppointmentPostDto;
import com.medibooking.bookingserver.dtos.appointment.AppointmentPutDto;
import com.medibooking.bookingserver.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/management/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentGetDto> add(@RequestBody AppointmentPostDto appointmentPostDto) {
        AppointmentGetDto appointmentGetDto = appointmentService.createAppointment(appointmentPostDto);
        return ResponseEntity.ok(appointmentGetDto);
    }

    @PutMapping("/{appointmentId}")
    public ResponseEntity<AppointmentGetDto> modify(@PathVariable Long appointmentId) {
        AppointmentGetDto appointmentGetDto = appointmentService.cancelAppointment(appointmentId);
        return ResponseEntity.ok(appointmentGetDto);
    }

    @DeleteMapping("/{appointmentId}")
    public ResponseEntity delete(@PathVariable Long appointmentId) {
        appointmentService.deleteAppointment(appointmentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_PATIENT')")
    public ResponseEntity<List<AppointmentGetDto>> getAll() {
        List<AppointmentGetDto> appointmentList = appointmentService.getAll();
        return ResponseEntity.ok(appointmentList);
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<AppointmentGetDto> findById(@PathVariable Long appointmentId) {
        return ResponseEntity.ok(appointmentService.findAppointmentById(appointmentId));
    }

    @GetMapping(value = "/search", params = "patientId")
    public ResponseEntity<List<AppointmentGetDto>> findByPatient(@RequestParam Long patientId) {
        return ResponseEntity.ok(appointmentService.findAppointmentsByPatient(patientId));
    }

    @GetMapping(value = "/search", params = "doctorId")
    public ResponseEntity<List<AppointmentGetDto>> findByDoctor(@RequestParam Long doctorId) {
        return ResponseEntity.ok(appointmentService.findAppointmentsByDoctor(doctorId));
    }

    @GetMapping(value = "/search", params = {"patientId", "date"})
    public ResponseEntity<List<AppointmentGetDto>> findByPatientAndDate(@RequestParam Long patientId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(appointmentService.findAppointmentsOfAPatientByDate(patientId, date));
    }

    @GetMapping(value = "/search", params = {"doctorId", "date"})
    public ResponseEntity<List<AppointmentGetDto>> findByDoctorAndDate(@RequestParam Long doctorId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(appointmentService.findAppointmentsOfADoctorByDate(doctorId, date));
    }

    @GetMapping(value = "/search", params = {"date"})
    public ResponseEntity<List<AppointmentGetDto>> findByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(appointmentService.findAppointmentsByDate(date));
    }
}
