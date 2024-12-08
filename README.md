# Producia (Gestion des produits)

##  Description du Projet
Ce projet est une API REST sécurisée permettant la gestion des produits, des catégories, et des utilisateurs,
avec des fonctionnalités adaptées aux rôles administrateurs et utilisateurs.

## Objectif Général de l'Application
L'objectif principal de cette application est de fournir une API REST robuste pour :
- Gérer les produits et les catégories.
- Gérer les utilisateurs et leurs rôles.
- Offrir une sécurité renforcée grâce à l'authentification et au contrôle des accès.
- Intégrer des outils pour le déploiement continu.


## Structure du Projet
- **Controllers :** Gère les requêtes HTTP REST.
- **Services :** Contient la logique métier.
- **Repositories :** Interagit avec la base de données.
- **DTOs et Mappers :** Transforment les entités pour les réponses API.
- **Exceptions :** Gère les erreurs spécifiques à l'API.

## Entités Principales
### Produit
- `designation` (String)
- `prix` (Double)
- `quantite` (Integer)

### Catégorie
- `nom` (String)
- `description` (String)

### User
- `login` (String)
- `password` (String)
- `active` (Boolean)
- `role` (Enum)


### Relations
- Une catégorie peut avoir plusieurs produits.
- Un produit appartient à une seule catégorie.

---



## Fonctionnalités du projet
### Gestion des Produits
1. **Lister les produits** avec pagination (USER ou ADMIN).
2. **Rechercher les produits par désignation** avec pagination et tri (USER ou ADMIN).
3. **Rechercher les produits par catégorie** (USER ou ADMIN).
4. **Filtrer les produits par catégorie** avec pagination et tri (USER ou ADMIN).
5. **Ajouter un produit** (ADMIN uniquement).
6. **Modifier un produit** existant (ADMIN uniquement).
7. **Supprimer un produit** (ADMIN uniquement).

**Endpoints :**
- `/api/user/products/**`
- `/api/admin/products/**`

### Gestion des Catégories
1. **Lister les catégories** avec pagination (USER ou ADMIN).
2. **Rechercher les catégories par nom** avec pagination et tri (USER ou ADMIN).
3. **Lister les produits d'une catégorie** avec pagination et tri (USER ou ADMIN).
4. **Ajouter une catégorie** (ADMIN uniquement).
5. **Modifier une catégorie** existante (ADMIN uniquement).
6. **Supprimer une catégorie** (ADMIN uniquement).

**Endpoints :**
- `/api/user/categories/**`
- `/api/admin/categories/**`

### Gestion des Utilisateurs
1. **Authentification :** `/api/auth/login`
2. **Création de compte :** `POST /api/auth/register`
3. **Lister les utilisateurs :** `GET /api/admin/users` (ADMIN uniquement).
4. **Gestion des rôles :** `PUT /api/admin/users/{id}/roles` (ADMIN uniquement).

---

## Sécurité
- **Authentification Stateful :** Basée sur session via `JdbcAuthentication`.
- **Cryptage des mots de passe :** Utilisation de `BCryptPasswordEncoder`.
- **Restrictions d'accès :**
    - `/api/admin/*` nécessite le rôle ADMIN.
    - `/api/user/*` nécessite le rôle USER.
- **Profils :**
    - **Test :** Désactive ou simplifie la sécurité pour le développement.
    - **Prod :** Active la sécurité avec `JdbcAuthentication`.

---

## Technologies Utilisées
- **Frameworks :** Spring Boot, Spring Security, Spring Data JPA.
- **Base de données :** MariaDB.
- **Langage :** Java 8+.
- **CI/CD :** Jenkins.
- **Conteneurisation :** Docker.
- **Tests :** JUnit, Mockito, Postman.
- **Documentation API :** Swagger.
- **Outils de travail :** Git (branches), JIRA (Scrum), SonarLint.

---

## Guide d'Installation et d'Exécution

### Prérequis
- Java 8 ou supérieur
- Maven
- Docker et Docker Compose

### Etapes à suivre 

1. Clonez le dépôt :
   ```bash
   git clone https://github.com/Douaesb/Producia.git
   cd Producia
2. Lancez les conteneurs Docker :
    ````
    docker-compose up

3. Accédez à l'application :

- Tester les endpoints via Postman ou autres outils.

## Planification Jira
- https://douaesb411.atlassian.net/jira/software/projects/PROD/boards/12/backlog?atlOrigin=eyJpIjoiNDA1OGMxNmYwZjM0NDdhOWFkZWVlYTQ0NmZjNmU3OWEiLCJwIjoiaiJ9

## Contact
Pour toute question ou suggestion, veuillez me contacter :

- **Name**: Douae sb
- **GitHub**: [Douaesb](https://github.com/Douaesb)