package com.xoslu.tech.consultationsappmongodb.repository;

import com.xoslu.tech.consultationsappmongodb.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;
import java.util.Optional;

// MongoRepository<Entité, TypeDeLId>
public interface PatientRepository extends MongoRepository<Patient, String> {

    // Spring génère la requête depuis le nom de la méthode
    Optional<Patient> findByEmail(String email);

    List<Patient> findByNom(String nom);

    // Champ imbriqué : adresse.ville
    List<Patient> findByAdresseVille(String ville);

    // Recherche insensible à la casse
    List<Patient> findByNomIgnoreCase(String nom);

    // Tableau contains
    List<Patient> findByAllergiesContaining(String allergie);

    // Requête Mongo native avec @Query
    @Query("{ 'groupeSanguin': ?0, 'adresse.ville': ?1 }")
    List<Patient> findByGroupeSanguinAndVille(String groupeSanguin, String ville);

    boolean existsByEmail(String email);
}

