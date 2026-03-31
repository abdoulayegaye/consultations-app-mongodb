package com.xoslu.tech.consultationsappmongodb.controller;

import com.xoslu.tech.consultationsappmongodb.model.Consultation;
import com.xoslu.tech.consultationsappmongodb.service.ConsultationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ConsultationController {

    private final ConsultationService consultationService;

    // GET /api/consultations
    @GetMapping("/consultations")
    public ResponseEntity<List<Consultation>> getAll() {
        return ResponseEntity.ok(consultationService.getAll());
    }

    // GET /api/consultations/{id}
    @GetMapping("/consultations/{id}")
    public ResponseEntity<Consultation> getById(@PathVariable String id) {
        return ResponseEntity.ok(consultationService.getById(id));
    }

    // POST /api/patients/{patientId}/consultations
    @PostMapping("/patients/{patientId}/consultations")
    public ResponseEntity<Consultation> create(
            @PathVariable String patientId,
            @Valid @RequestBody Consultation consultation) {
        Consultation created = consultationService.create(patientId, consultation);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /api/consultations/{id}
    @PutMapping("/consultations/{id}")
    public ResponseEntity<Consultation> update(
            @PathVariable String id,
            @Valid @RequestBody Consultation consultation) {
        return ResponseEntity.ok(consultationService.update(id, consultation));
    }

    // DELETE /api/consultations/{id}
    @DeleteMapping("/consultations/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        consultationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // GET /api/patients/{patientId}/consultations
    @GetMapping("/patients/{patientId}/consultations")
    public ResponseEntity<List<Consultation>> getByPatient(
            @PathVariable String patientId) {
        return ResponseEntity.ok(consultationService.getByPatient(patientId));
    }

    // GET /api/consultations/statut/{statut}
    @GetMapping("/consultations/statut/{statut}")
    public ResponseEntity<List<Consultation>> getByStatut(@PathVariable String statut) {
        return ResponseEntity.ok(consultationService.getByStatut(statut));
    }
}

