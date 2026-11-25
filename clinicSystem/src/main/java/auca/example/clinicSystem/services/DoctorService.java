package auca.example.clinicSystem.services;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import auca.example.clinicSystem.model.Doctor;
import auca.example.clinicSystem.model.Office;
import auca.example.clinicSystem.repository.DoctorRepository;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final OfficeService officeService;

    public DoctorService(DoctorRepository doctorRepository, OfficeService officeService) {
        this.doctorRepository = doctorRepository;
        this.officeService = officeService;
    }

    public Doctor create(Doctor doctor, UUID officeId) {
        doctor.setOffice(checkOfficeFree(officeId, null));
        return doctorRepository.save(doctor);
    }

    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    public Doctor findById(UUID id) {
        return doctorRepository.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found"));
    }

    public Doctor update(UUID id, Doctor data, UUID officeId) {
        Doctor doctor = findById(id);
        doctor.setFirstName(data.getFirstName());
        doctor.setLastName(data.getLastName());
        doctor.setDateOfBirth(data.getDateOfBirth());
        if (officeId != null) {
            doctor.setOffice(checkOfficeFree(officeId, doctor));
        }
        return doctorRepository.save(doctor);
    }

    public void delete(UUID id) {
        doctorRepository.delete(findById(id));
    }

    // 1-1 rule: an office can belong to only one doctor
    private Office checkOfficeFree(UUID officeId, Doctor current) {
        Office office = officeService.findById(officeId);
        if (office.getDoctor() != null && !office.getDoctor().equals(current)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                "Office already has a doctor");
        }
        return office;
    }
}