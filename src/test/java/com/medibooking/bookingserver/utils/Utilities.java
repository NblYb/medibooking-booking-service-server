package com.medibooking.bookingserver.utils;


import com.medibooking.bookingserver.dtos.appointment.AppointmentGetDto;
import com.medibooking.bookingserver.dtos.appointment.AppointmentPostDto;
import com.medibooking.bookingserver.dtos.appointment.AppointmentPutDto;
import com.medibooking.bookingserver.dtos.doctor.DoctorGetDto;
import com.medibooking.bookingserver.dtos.doctor.DoctorPostDto;
import com.medibooking.bookingserver.dtos.doctor.DoctorPutDto;
import com.medibooking.bookingserver.dtos.language.LanguageGetDto;
import com.medibooking.bookingserver.dtos.language.LanguagePostDto;
import com.medibooking.bookingserver.dtos.language.LanguagePutDto;
import com.medibooking.bookingserver.dtos.patient.PatientGetDto;
import com.medibooking.bookingserver.dtos.patient.PatientPostDto;
import com.medibooking.bookingserver.dtos.patient.PatientPutDto;
import com.medibooking.bookingserver.dtos.specialization.SpecializationGetDto;
import com.medibooking.bookingserver.dtos.specialization.SpecializationPostDto;
import com.medibooking.bookingserver.dtos.specialization.SpecializationPutDto;
import com.medibooking.bookingserver.entities.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public class Utilities {

    public DoctorGetDto buildDoctorGetDto (Long id,
                                           int age,
                                           String gender,
                                           String firstName,
                                           String lastName,
                                           String description,
                                           Set<LanguageGetDto> languages,
                                           Set<SpecializationGetDto> specializations){
        DoctorGetDto doctorGetDto = new DoctorGetDto();
        doctorGetDto.setId(id);
        doctorGetDto.setAge(age);
        doctorGetDto.setGender(gender);
        doctorGetDto.setFirstName(firstName);
        doctorGetDto.setLastName(lastName);
        doctorGetDto.setDescription(description);
        doctorGetDto.setLanguages(languages);
        doctorGetDto.setSpecializations(specializations);
        return doctorGetDto;
    }

    public DoctorPutDto buildDoctorPutDto (Long id,
                                           int age,
                                           String gender,
                                           String firstName,
                                           String lastName,
                                           String description,
                                           Set<LanguageGetDto> languages,
                                           Set<SpecializationGetDto> specializations){
        DoctorPutDto doctorPutDto = new DoctorPutDto();
        doctorPutDto.setId(id);
        doctorPutDto.setAge(age);
        doctorPutDto.setGender(gender);
        doctorPutDto.setFirstName(firstName);
        doctorPutDto.setLastName(lastName);
        doctorPutDto.setDescription(description);
        doctorPutDto.setLanguages(languages);
        doctorPutDto.setSpecializations(specializations);
        return doctorPutDto;
    }

    public DoctorPostDto buildDoctorPostDto (int age,
                                             String gender,
                                             String firstName,
                                             String lastName,
                                             String description,
                                             Set<LanguageGetDto> languages,
                                             Set<SpecializationGetDto> specializations){
        DoctorPostDto doctorPostDto = new DoctorPostDto();
        doctorPostDto.setAge(age);
        doctorPostDto.setGender(gender);
        doctorPostDto.setFirstName(firstName);
        doctorPostDto.setLastName(lastName);
        doctorPostDto.setDescription(description);
        doctorPostDto.setLanguages(languages);
        doctorPostDto.setSpecializations(specializations);
        return doctorPostDto;
    }

    public AppointmentGetDto buildAppointmentGetDto (Long id,
                                                     LocalDate date,
                                                     LocalTime startingTime,
                                                     LocalTime endingTime,
                                                     String notes,
                                                     Boolean isCancelled,
                                                     Long patient,
                                                     String patientFirstName,
                                                     String patientLastName,
                                                     Long doctor,
                                                     String doctorFirstName,
                                                     String doctorLastName){
        AppointmentGetDto appointmentGetDto = new AppointmentGetDto();
        appointmentGetDto.setId(id);
        appointmentGetDto.setDate(date);
        appointmentGetDto.setStartingTime(startingTime);
        appointmentGetDto.setEndingTime(endingTime);
        appointmentGetDto.setNotes(notes);
        appointmentGetDto.setIsCancelled(isCancelled);
        appointmentGetDto.setPatient(patient);
        appointmentGetDto.setPatientFirstName(patientFirstName);
        appointmentGetDto.setPatientLastName(patientLastName);
        appointmentGetDto.setDoctor(doctor);
        appointmentGetDto.setDoctorFirstName(doctorFirstName);
        appointmentGetDto.setDoctorLastName(doctorLastName);
        return appointmentGetDto;
    }

    public AppointmentPutDto buildAppointmentPutDto (Long id,
                                                     LocalDate date,
                                                     LocalTime startingTime,
                                                     LocalTime endingTime,
                                                     String notes,
                                                     Boolean isCancelled,
                                                     Long patient,
                                                     Long doctor){
        AppointmentPutDto appointmentPutDto = new AppointmentPutDto();
        appointmentPutDto.setId(id);
        appointmentPutDto.setDate(date);
        appointmentPutDto.setStartingTime(startingTime);
        appointmentPutDto.setEndingTime(endingTime);
        appointmentPutDto.setNotes(notes);
        appointmentPutDto.setIsCancelled(isCancelled);
        appointmentPutDto.setPatient(patient);
        appointmentPutDto.setDoctor(doctor);
        return appointmentPutDto;
    }

    public AppointmentPostDto buildAppointmentPostDto (LocalDate date,
                                                       LocalTime startingTime,
                                                       LocalTime endingTime,
                                                       String notes,
                                                       Boolean isCancelled,
                                                       Long patient,
                                                       Long doctor){
        AppointmentPostDto appointmentPostDto = new AppointmentPostDto();
        appointmentPostDto.setDate(date);
        appointmentPostDto.setStartingTime(startingTime);
        appointmentPostDto.setEndingTime(endingTime);
        appointmentPostDto.setNotes(notes);
        appointmentPostDto.setIsCancelled(isCancelled);
        appointmentPostDto.setPatient(patient);
        appointmentPostDto.setDoctor(doctor);
        return appointmentPostDto;
    }

    public PatientGetDto buildPatientGetDto (Long id,
                                             int age,
                                             String gender,
                                             String firstName,
                                             String lastName,
                                             String email,
                                             Long accountId){
        PatientGetDto patientGetDto= new PatientGetDto();
        patientGetDto.setId(id);
        patientGetDto.setAge(age);
        patientGetDto.setGender(gender);
        patientGetDto.setFirstName(firstName);
        patientGetDto.setLastName(lastName);
        patientGetDto.setEmail(email);
        patientGetDto.setAccountId(accountId);
        return patientGetDto;
    }

    public PatientPostDto buildPatientPostDto (int age,
                                               String gender,
                                               String firstName,
                                               String lastName,
                                               String email,
                                               Long accountId){
        PatientPostDto patientPostDto = new PatientPostDto();
        patientPostDto.setAge(age);
        patientPostDto.setGender(gender);
        patientPostDto.setFirstName(firstName);
        patientPostDto.setLastName(lastName);
        patientPostDto.setEmail(email);
        patientPostDto.setAccountId(accountId);
        return patientPostDto;
    }

    public PatientPutDto buildPatientPutDto (Long id,
                                             int age,
                                             String gender,
                                             String firstName,
                                             String lastName,
                                             String email){
        PatientPutDto patientPutDto= new PatientPutDto();
        patientPutDto.setId(id);
        patientPutDto.setAge(age);
        patientPutDto.setGender(gender);
        patientPutDto.setFirstName(firstName);
        patientPutDto.setLastName(lastName);
        patientPutDto.setEmail(email);
        return patientPutDto;
    }

    public LanguageGetDto buildLanguageGetDto (Long id,
                                               String languageName){
        LanguageGetDto languageGetDto = new LanguageGetDto();
        languageGetDto.setId(id);
        languageGetDto.setLanguageName(languageName);
        return languageGetDto;
    }

    public LanguagePutDto buildLanguagePutDto (Long id,
                                               String languageName){
        LanguagePutDto languagePutDto = new LanguagePutDto();
        languagePutDto.setId(id);
        languagePutDto.setLanguageName(languageName);
        return languagePutDto;
    }

    public LanguagePostDto buildLanguagePostDto (String languageName){
        LanguagePostDto languagePostDto = new LanguagePostDto();
        languagePostDto.setLanguageName(languageName);
        return languagePostDto;
    }

    public SpecializationGetDto buildSpecializationGetDto (Long id,
                                                      String languageName){
        SpecializationGetDto specializationGetDto = new SpecializationGetDto();
        specializationGetDto.setId(id);
        specializationGetDto.setSpecializationName(languageName);
        return specializationGetDto;
    }

    public SpecializationPostDto buildSpecializationPostDto (String languageName){
        SpecializationPostDto specializationPostDto = new SpecializationPostDto();
        specializationPostDto.setSpecializationName(languageName);
        return specializationPostDto;
    }
    public SpecializationPutDto buildSpecializationPutDto (Long id,
                                                               String languageName){
        SpecializationPutDto specializationPutDto = new SpecializationPutDto();
        specializationPutDto.setId(id);
        specializationPutDto.setSpecializationName(languageName);
        return specializationPutDto;
    }
    public Doctor buildDoctor(int age,
                              String gender,
                              String firstName,
                              String lastName) {

        Doctor doctor = new Doctor();
        doctor.setGender(gender);
        doctor.setAge(age);
        doctor.setFirstName(firstName);
        doctor.setLastName(lastName);
        return doctor;
    }

    public Language buildLanguage(String languageName) {

        Language language = new Language();
        language.setLanguageName(languageName);
        return language;
    }

    public Patient buildPatient(int age,
                                String gender,
                                String firstName,
                                String lastName) {

        Patient patient = new Patient();
        patient.setGender(gender);
        patient.setAge(age);
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        return patient;
    }

    public Patient buildPatientWithId(Long id,
                                      int age,
                                      String gender,
                                      String firstName,
                                      String lastName) {

        Patient patient = new Patient();
        patient.setId(id);
        patient.setGender(gender);
        patient.setAge(age);
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        return patient;
    }

    public Doctor buildDoctorWithId(Long id,
                                    int age,
                                    String gender,
                                    String firstName,
                                    String lastName) {

        Doctor doctor = new Doctor();
        doctor.setId(id);
        doctor.setGender(gender);
        doctor.setAge(age);
        doctor.setFirstName(firstName);
        doctor.setLastName(lastName);
        return doctor;
    }

    public Specialization buildSpecialization(Long id, String specialization_name) {

        Specialization specialization = new Specialization();
        specialization.setId(id);;
        specialization.setSpecializationName(specialization_name);
        return specialization;
    }

    public Appointment buildAppointment(Long id,
                                        LocalDate date,
                                        LocalTime startingTime,
                                        LocalTime endingTime,
                                        String notes,
                                        Boolean isCancelled) {
        Appointment appointment=new Appointment();
        appointment.setId(id);
        appointment.setDate(date);
        appointment.setStartingTime(startingTime);
        appointment.setEndingTime(endingTime);
        appointment.setNotes(notes);
        appointment.setIsCancelled(isCancelled);
        return appointment;
    }
}
