package com.medibooking.bookingserver.controllers;

import com.medibooking.bookingserver.dtos.patient.PatientGetDto;
import com.medibooking.bookingserver.dtos.patient.PatientPostDto;
import com.medibooking.bookingserver.dtos.patient.PatientPutDto;
import com.medibooking.bookingserver.services.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientGetDto> add(@RequestBody PatientPostDto patientPostDto) {
        PatientGetDto patientGetDto = patientService.create(patientPostDto);
        return ResponseEntity.ok(patientGetDto);
    }

    @GetMapping
    public ResponseEntity<List<PatientGetDto>> find() {
        List<PatientGetDto> list = patientService.getAllPatients();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/patientId/{patientId}")
    public ResponseEntity<PatientGetDto> findById(@PathVariable Long patientId) {
        return ResponseEntity.ok(patientService.findPatientById(patientId));
    }

    @GetMapping(value = "/search", params = "accountId")
    public ResponseEntity<PatientGetDto> findByAccountId(@RequestParam Long accountId){
        return ResponseEntity.ok(patientService.findPatientByAccountId(accountId));
    }

    @GetMapping(value = "/search", params = "patientName")
    public ResponseEntity<PatientGetDto> findByName(@RequestParam String patientName) {
        return ResponseEntity.ok(patientService.findPatientByName(patientName));
    }

    @PutMapping("/{patientId}")
    public ResponseEntity<PatientGetDto> update(@PathVariable Long patientId, @RequestBody PatientPutDto patientPutDto) {
        return ResponseEntity.ok(patientService.modify(patientId, patientPutDto));
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity delete(@PathVariable Long patientId) {
        patientService.delete(patientId);
        return ResponseEntity.ok().build();
    }
}
