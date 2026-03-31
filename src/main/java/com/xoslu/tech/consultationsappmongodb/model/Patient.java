package com.xoslu.tech.consultationsappmongodb.model;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.List;

@Document(collection = "patients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {

    @Id
    private String id;  // MongoDB génère un ObjectId automatiquement

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;

    @Email(message = "Email invalide")
    @Indexed(unique = true)  // Index unique sur email
    private String email;

    @NotNull
    private LocalDate dateNaissance;

    private String telephone;

    // Objet imbriqué — stocké directement dans le document
    private Adresse adresse;

    // Tableau natif MongoDB
    private List<String> allergies;

    private String groupeSanguin;
}

