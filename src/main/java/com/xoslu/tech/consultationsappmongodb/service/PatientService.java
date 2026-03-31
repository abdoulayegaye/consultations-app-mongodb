package com.xoslu.tech.consultationsappmongodb.service;

import com.xoslu.tech.consultationsappmongodb.exception.ResourceNotFoundException;
import com.xoslu.tech.consultationsappmongodb.model.Patient;
import com.xoslu.tech.consultationsappmongodb.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    public Patient getById(String id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Patient non trouvé avec l'id : " + id));
    }

    public Patient create(Patient patient) {
        if (patientRepository.existsByEmail(patient.getEmail())) {
            throw new IllegalArgumentException(
                    "Un patient avec cet email existe déjà");
        }
        return patientRepository.save(patient);
    }

    public Patient update(String id, Patient updated) {
        Patient existing = getById(id);
        existing.setNom(updated.getNom());
        existing.setPrenom(updated.getPrenom());
        existing.setDateNaissance(updated.getDateNaissance());
        existing.setTelephone(updated.getTelephone());
        existing.setAdresse(updated.getAdresse());
        existing.setAllergies(updated.getAllergies());
        existing.setGroupeSanguin(updated.getGroupeSanguin());
        return patientRepository.save(existing);
    }

    public void delete(String id) {
        getById(id); // Vérifie l'existence
        patientRepository.deleteById(id);
    }

    public List<Patient> findByVille(String ville) {
        return patientRepository.findByAdresseVille(ville);
    }
}
