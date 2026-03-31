package com.xoslu.tech.consultationsappmongodb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Pas d'annotation @Document — imbriqué dans Patient
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Adresse {
    private String rue;
    private String ville;
    private String codePostal;
    private String pays;
}
