package auca.example.clinicSystem.services;

import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import auca.example.clinicSystem.model.Office;
import auca.example.clinicSystem.repository.OfficeRepository;

@Service
public class OfficeService {

    private final OfficeRepository officeRepository;

    public OfficeService(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    public Office create(Office office) {
        return officeRepository.save(office);
    }

    public List<Office> findAll() {
        return officeRepository.findAll();
    }

    public Office findById(UUID id) {
        return officeRepository.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Office not found"));
    }

    public Office update(UUID id, Office data) {
        Office office = findById(id);
        office.setName(data.getName());
        office.setOfficeNumber(data.getOfficeNumber());
        return officeRepository.save(office);
    }

    public void delete(UUID id) {
        Office office = findById(id);
        if (office.getDoctor() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                "Office is assigned to a doctor");
        }
        officeRepository.delete(office);
    }
}