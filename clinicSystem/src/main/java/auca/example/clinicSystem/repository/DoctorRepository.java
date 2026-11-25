package auca.example.clinicSystem.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import auca.example.clinicSystem.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {}