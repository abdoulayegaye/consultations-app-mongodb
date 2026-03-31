package com.xoslu.tech.consultationsappmongodb.service;

import com.xoslu.tech.consultationsappmongodb.enums.Statut;
import com.xoslu.tech.consultationsappmongodb.exception.ResourceNotFoundException;
import com.xoslu.tech.consultationsappmongodb.model.Consultation;
import com.xoslu.tech.consultationsappmongodb.model.Patient;
import com.xoslu.tech.consultationsappmongodb.repository.ConsultationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultationService {

    private final ConsultationRepository consultationRepository;
    private final PatientService patientService;

    public List<Consultation> getAll() {
        return consultationRepository.findAll();
    }

    public Consultation getById(String id) {
        return consultationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Consultation non trouvée : " + id));
    }

    public Consultation create(String patientId, Consultation consultation) {
        Patient patient = patientService.getById(patientId);
        consultation.setPatient(patient);
        if (consultation.getStatut() == null) {
            consultation.setStatut(Statut.PLANIFIEE);
        }
        return consultationRepository.save(consultation);
    }

    public Consultation update(String id, Consultation updated) {
        Consultation existing = getById(id);
        existing.setDateConsultation(updated.getDateConsultation());
        existing.setMotif(updated.getMotif());
        existing.setDiagnostic(updated.getDiagnostic());
        existing.setTraitement(updated.getTraitement());
        existing.setNotes(updated.getNotes());
        existing.setStatut(updated.getStatut());
        return consultationRepository.save(existing);
    }

    public void delete(String id) {
        getById(id);
        consultationRepository.deleteById(id);
    }

    public List<Consultation> getByPatient(String patientId) {
        return consultationRepository.findByPatientId(patientId);
    }

    public List<Consultation> getByStatut(String statut) {
        try {
            return consultationRepository.findByStatut(Statut.valueOf(statut.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Statut invalide : " + statut +
                    ". Valeurs acceptées : PLANIFIEE, EN_COURS, TERMINEE, ANNULEE");
        }
    }
}
