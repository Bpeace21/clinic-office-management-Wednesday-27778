package auca.example.clinicSystem.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import auca.example.clinicSystem.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, UUID> {}