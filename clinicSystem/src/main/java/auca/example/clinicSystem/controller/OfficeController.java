package auca.example.clinicSystem.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import auca.example.clinicSystem.model.Office;
import auca.example.clinicSystem.services.OfficeService;

@RestController
@RequestMapping("/api/offices")
@CrossOrigin(origins = "http://localhost:5173")
public class OfficeController {

    private final OfficeService officeService;

    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Office create(@RequestBody Office office) {
        return officeService.create(office);
    }

    @GetMapping
    public List<Office> findAll() {
        return officeService.findAll();
    }

    @GetMapping("/{id}")
    public Office findById(@PathVariable UUID id) {
        return officeService.findById(id);
    }

    @PutMapping("/{id}")
    public Office update(@PathVariable UUID id, @RequestBody Office office) {
        return officeService.update(id, office);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        officeService.delete(id);
    }
}