package com.medibooking.bookingserver.controllers;

import com.medibooking.bookingserver.dtos.doctor.DoctorGetDto;
import com.medibooking.bookingserver.dtos.doctor.DoctorPostDto;
import com.medibooking.bookingserver.dtos.doctor.DoctorPutDto;
import com.medibooking.bookingserver.services.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody DoctorPostDto doctorPostDto) {
        DoctorGetDto doctorGetDto = doctorService.create(doctorPostDto);
        return ResponseEntity.ok(doctorGetDto);
    }

    @GetMapping
    public ResponseEntity<List<DoctorGetDto>> find() {
        List<DoctorGetDto> list = doctorService.getAllDoctors();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<DoctorGetDto> findById(@PathVariable Long doctorId) {
        return ResponseEntity.ok(doctorService.findDoctorById(doctorId));
    }

    @GetMapping(value = "/search", params = "accountId")
    public ResponseEntity<DoctorGetDto> findByAccountId(@RequestParam Long accountId) {
        return ResponseEntity.ok(doctorService.findDoctorByAccountId(accountId));
    }

    @PutMapping("/{doctorId}")
    public ResponseEntity<DoctorGetDto> update(@PathVariable Long doctorId, @RequestBody DoctorPutDto doctorPutDto) {
        return ResponseEntity.ok(doctorService.modify(doctorId, doctorPutDto));
    }

    @DeleteMapping("/{doctorId}")
    public ResponseEntity delete(@PathVariable Long doctorId) {
        doctorService.delete(doctorId);
        return ResponseEntity.ok().build();
    }
}
