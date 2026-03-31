package com.xoslu.tech.consultationsappmongodb.repository;

import com.xoslu.tech.consultationsappmongodb.enums.Statut;
import com.xoslu.tech.consultationsappmongodb.model.Consultation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.time.LocalDateTime;
import java.util.List;

public interface ConsultationRepository extends MongoRepository<Consultation, String> {

    // Toutes les consultations d'un patient
    List<Consultation> findByPatientId(String patientId);

    // Par statut
    List<Consultation> findByStatut(Statut statut);

    // Par plage de dates
    List<Consultation> findByDateConsultationBetween(
            LocalDateTime debut, LocalDateTime fin);

    // Contient un mot dans le diagnostic
    List<Consultation> findByDiagnosticContainingIgnoreCase(String keyword);

    // Compter les consultations d'un patient
    long countByPatientId(String patientId);

    // Requête Mongo native
    @Query("{ 'patient.$id': { $oid: ?0 }, 'statut': ?1 }")
    List<Consultation> findByPatientIdAndStatut(String patientId, String statut);
}

