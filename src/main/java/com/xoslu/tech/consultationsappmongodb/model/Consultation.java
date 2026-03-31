package com.xoslu.tech.consultationsappmongodb.model;

import com.xoslu.tech.consultationsappmongodb.enums.Statut;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "consultations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consultation {

    @Id
    private String id;

    // @DBRef = référence vers un document Patient
    // (équivalent d'une Foreign Key en SQL)
    @DBRef
    //@NotNull(message = "Le patient est obligatoire")
    private Patient patient;

    @NotNull
    private LocalDateTime dateConsultation;

    @NotBlank
    private String motif;

    private String diagnostic;
    private String traitement;
    private String notes;

    // Enum stocké comme String dans MongoDB
    private Statut statut;

    @CreatedDate
    private LocalDateTime createdAt;
}
