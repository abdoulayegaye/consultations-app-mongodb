# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commandes essentielles

```bash
# Lancer l'application
./mvnw spring-boot:run

# Compiler et packager
./mvnw clean package

# Lancer tous les tests
./mvnw test

# Lancer un test spécifique
./mvnw test -Dtest=ConsultationsAppMongodbApplicationTests

# Construire l'image Docker
./mvnw spring-boot:build-image
```

L'application tourne sur le port **8085**. MongoDB doit être disponible sur `localhost:27017`, base de données `consultations_db`.

## Architecture

Application Spring Boot 4.0.5 (Java 21) avec MongoDB, structurée en couches classiques :

```
controller/ → service/ → repository/ → MongoDB
```

- **`model/`** : entités MongoDB (`Patient`, `Consultation`, `Adresse` comme objet imbriqué)
- **`repository/`** : interfaces Spring Data MongoDB avec méthodes de requête et `@Query` natif
- **`service/`** : logique métier (unicité email, liaison consultation↔patient, statut par défaut)
- **`controller/`** : endpoints REST avec validation Jakarta
- **`enums/`** : `Statut` (PLANIFIEE, EN_COURS, TERMINEE, ANNULEE)
- **`exception/`** : `ResourceNotFoundException` pour les 404

## Modèle de données

**Patient** (`@Document("patients")`) → contient une `Adresse` imbriquée, une liste `allergies`, et un index unique sur `email`.

**Consultation** (`@Document("consultations")`) → référence `Patient` via `@DBRef`. Le statut est initialisé à `PLANIFIEE` à la création.

## Endpoints REST

| Méthode | URL | Description |
|---------|-----|-------------|
| GET/POST | `/api/patients` | Liste / création |
| GET/PUT/DELETE | `/api/patients/{id}` | Détail / modification / suppression |
| GET | `/api/patients/ville/{ville}` | Filtrer par ville |
| GET/POST | `/api/patients/{patientId}/consultations` | Consultations d'un patient |
| GET/PUT/DELETE | `/api/consultations/{id}` | Détail / modification / suppression |
| GET | `/api/consultations/statut/{statut}` | Filtrer par statut |

## Points clés d'implémentation

- Lombok (`@Data`, `@Builder`, `@RequiredArgsConstructor`) est utilisé massivement — ne pas écrire les getters/setters manuellement.
- La vérification d'unicité de l'email patient se fait dans `PatientService` avant la sauvegarde.
- Les consultations sont toujours créées via `POST /api/patients/{patientId}/consultations` (pas de création directe sans patient).
- Le logging MongoDB est en mode DEBUG dans `application.yaml` — les requêtes sont visibles dans les logs.
