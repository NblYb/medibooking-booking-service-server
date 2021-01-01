package com.medibooking.bookingserver.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.medibooking.bookingserver.dtos.appointment.AppointmentGetDto;
import com.medibooking.bookingserver.dtos.appointment.AppointmentPostDto;
import com.medibooking.bookingserver.entities.Appointment;
import com.medibooking.bookingserver.entities.Doctor;
import com.medibooking.bookingserver.entities.Patient;
import com.medibooking.bookingserver.mappers.AppointmentMapper;
import com.medibooking.bookingserver.repositories.AppointmentRepository;
import com.medibooking.bookingserver.repositories.DoctorRepository;
import com.medibooking.bookingserver.repositories.PatientRepository;
import com.medibooking.messages.email.Email;
import com.medibooking.messages.email.EmailSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Autowired
    RabbitTemplate rabbitTemplate;

    public AppointmentGetDto createAppointment(AppointmentPostDto appointmentPostDto) {
        Appointment appointment = appointmentMapper.toEntity(appointmentPostDto);
        Appointment dbAppointment = appointmentRepository.save(appointment);
        Patient patient = patientRepository.getOne(dbAppointment.getPatient().getId());
        Doctor doctor = doctorRepository.getOne(dbAppointment.getDoctor().getId());
        if (dbAppointment.getId() != null){
            try {
                sendEmailMessage(dbAppointment, patient, doctor);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        AppointmentGetDto appointmentGetDto = appointmentMapper.fromEntity(dbAppointment);
        appointmentGetDto.setDoctorFirstName(doctor.getFirstName());
        appointmentGetDto.setDoctorLastName(doctor.getLastName());
        appointmentGetDto.setPatientFirstName(patient.getFirstName());
        appointmentGetDto.setPatientLastName(patient.getLastName());
        return appointmentGetDto;
    }

    private void sendEmailMessage(Appointment appointment, Patient patient, Doctor doctor) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module =
                new SimpleModule("CustomEmailSerializer", new Version(1, 0, 0, null, null, null));
        module.addSerializer(Email.class, new EmailSerializer());
        objectMapper.registerModule(module);
        Email emailMessage = new Email();
        emailMessage.setEmail(patient.getEmail());
        emailMessage.setDate(appointment.getDate());
        emailMessage.setStartingTime(appointment.getStartingTime());
        emailMessage.setNotes(appointment.getNotes());
        emailMessage.setPatientFirstName(patient.getFirstName());
        emailMessage.setPatientLastName(patient.getLastName());
        emailMessage.setDoctorFirstName(doctor.getFirstName());
        emailMessage.setDoctorLastName(doctor.getLastName());
        String emailJson = objectMapper.writeValueAsString(emailMessage);
        rabbitTemplate.convertAndSend("AppointmentEmail", emailJson);
    }

    public AppointmentGetDto cancelAppointment(Long appointmentId){
        Appointment appointment = appointmentRepository.getOne(appointmentId);
        appointment.setIsCancelled(true);
        return appointmentMapper.fromEntity(appointmentRepository.save(appointment));
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    public List<AppointmentGetDto> getAll() {
        return appointmentMapper.fromEntities(appointmentRepository.findAll());
    }

    public AppointmentGetDto findAppointmentById(Long id) {
        return appointmentMapper.fromEntity(appointmentRepository.getOne(id));
    }

    public List<AppointmentGetDto> findAppointmentsByPatient(Long patientId) {
        return appointmentMapper.fromEntities(appointmentRepository.findAppointmentsByPatientId(patientId));
    }

    public List<AppointmentGetDto> findAppointmentsByDoctor(Long doctorId) {
        return appointmentMapper.fromEntities(appointmentRepository.findAppointmentsByDoctorId(doctorId));
    }

    public List<AppointmentGetDto> findAppointmentsOfAPatientByDate(Long patientId, LocalDate date) {
        return appointmentMapper.fromEntities(appointmentRepository.findAppointmentsOfAPatientByDate(patientId, date));
    }

    public List<AppointmentGetDto> findAppointmentsOfADoctorByDate(Long doctorId, LocalDate date) {
        return appointmentMapper.fromEntities(appointmentRepository.findAppointmentsOfADoctorByDate(doctorId, date));
    }

    public List<AppointmentGetDto> findAppointmentsByDate(LocalDate date) {
        return appointmentMapper.fromEntities(appointmentRepository.findAppointmentsByDate(date));
    }
}
