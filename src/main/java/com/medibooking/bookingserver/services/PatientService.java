package com.medibooking.bookingserver.services;

import com.medibooking.bookingserver.dtos.patient.PatientGetDto;
import com.medibooking.bookingserver.dtos.patient.PatientPostDto;
import com.medibooking.bookingserver.dtos.patient.PatientPutDto;
import com.medibooking.bookingserver.entities.Patient;
import com.medibooking.bookingserver.mappers.PatientMapper;
import com.medibooking.bookingserver.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public PatientGetDto create(PatientPostDto patientPostDto) {
        Patient patientEntity = patientMapper.toEntity(patientPostDto);
        return patientMapper.fromEntity(patientRepository.save(patientEntity));
    }

//    @RabbitListener(queues = "PatientSignUp")
//    public void createPatientOnSignUp(com.medibooking.messages.Patient patient){
//        Patient dbPatient = new Patient();
//        dbPatient.setAccountId(patient.getAccountId());
//        dbPatient.setAge(patient.getAge());
//        dbPatient.setFirstName(patient.getFirstName());
//        dbPatient.setLastName(patient.getLastName());
//        dbPatient.setGender(patient.getGender());
//        patientRepository.save(dbPatient);
//    }

    public PatientGetDto modify(Long patientId, PatientPutDto patientPutDto) {
        Patient patient = new Patient();
        patientMapper.copy(patientPutDto, patient);
        patient.setId(patientId);
        patient.setAccountId(findPatientAccountIdByPatientId(patientId));
        return patientMapper.fromEntity(patientRepository.save(patient));
    }

    public List<PatientGetDto> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(patientMapper::fromEntity)
                .collect(Collectors.toList());
    }

    public PatientGetDto findPatientById(Long id) {
        return patientMapper.fromEntity(patientRepository.getOne(id));
    }

    public Long findPatientAccountIdByPatientId(Long patientId){
        Patient patient = patientRepository.getOne(patientId);
        return patient.getAccountId();
    }

    public PatientGetDto findPatientByAccountId(Long accountId) {
        return patientMapper.fromEntity(patientRepository.findByAccountId(accountId));
    }

    public PatientGetDto findPatientByName(String name) {
        return patientMapper.fromEntity(patientRepository.findByFirstName(name));
    }

    public void delete(Long id) {
        patientRepository.deleteById(id);
    }
}
