package auca.example.clinicSystem.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import auca.example.clinicSystem.model.Office;

public interface OfficeRepository extends JpaRepository<Office, UUID> {}
