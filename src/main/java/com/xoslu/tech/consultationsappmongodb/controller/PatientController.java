package com.xoslu.tech.consultationsappmongodb.controller;

import com.xoslu.tech.consultationsappmongodb.model.Patient;
import com.xoslu.tech.consultationsappmongodb.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    // GET /api/patients
    @GetMapping
    public ResponseEntity<List<Patient>> getAll() {
        return ResponseEntity.ok(patientService.getAll());
    }

    // GET /api/patients/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getById(@PathVariable String id) {
        return ResponseEntity.ok(patientService.getById(id));
    }

    // POST /api/patients
    @PostMapping
    public ResponseEntity<Patient> create(@Valid @RequestBody Patient patient) {
        Patient created = patientService.create(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /api/patients/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Patient> update(
            @PathVariable String id,
            @Valid @RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.update(id, patient));
    }

    // DELETE /api/patients/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        patientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // GET /api/patients/ville/{ville}
    @GetMapping("/ville/{ville}")
    public ResponseEntity<List<Patient>> findByVille(@PathVariable String ville) {
        return ResponseEntity.ok(patientService.findByVille(ville));
    }
}

