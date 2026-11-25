package auca.example.clinicSystem.services;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import auca.example.clinicSystem.model.Patient;
import auca.example.clinicSystem.repository.PatientRepository;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient create(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Patient findById(UUID id) {
        return patientRepository.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));
    }

    public Patient update(UUID id, Patient data) {
        Patient patient = findById(id);
        patient.setFirstName(data.getFirstName());
        patient.setLastName(data.getLastName());
        patient.setDateOfBirth(data.getDateOfBirth());
        return patientRepository.save(patient);
    }

    public void delete(UUID id) {
        patientRepository.delete(findById(id));
    }
}