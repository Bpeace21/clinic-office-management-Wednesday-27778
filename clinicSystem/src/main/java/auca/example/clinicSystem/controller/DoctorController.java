package auca.example.clinicSystem.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import auca.example.clinicSystem.model.Doctor;
import auca.example.clinicSystem.services.DoctorService;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin(origins = "http://localhost:5173")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Doctor create(@RequestBody Doctor doctor, @RequestParam UUID officeId) {
        return doctorService.create(doctor, officeId);
    }

    @GetMapping
    public List<Doctor> findAll() {
        return doctorService.findAll();
    }

    @GetMapping("/{id}")
    public Doctor findById(@PathVariable UUID id) {
        return doctorService.findById(id);
    }

    @PutMapping("/{id}")
    public Doctor update(@PathVariable UUID id, @RequestBody Doctor doctor,
            @RequestParam(required = false) UUID officeId) {
        return doctorService.update(id, doctor, officeId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        doctorService.delete(id);
    }
}