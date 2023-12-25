# Chatop-backend -

## Contenu

- [Introduction](#introduction)
- [Technologies Utilisées](#technologies-utilisées)
- [Prérequis](#prérequis)
- [Installation](#installation)
- [Création et Lancement de la Base de Données avec Docker](#Création-et-Lancement-de-la-Base-de-Données-avec-Docker)
- [Documentation avec Swagger](#documentation-avec-swagger)

## Introduction

Bienvenue  sur le backend de Chatop, le moteur qui alimente un portail révolutionnaire facilitant la connexion entre locataires potentiels et propriétaires pour des locations saisonnières.  notre objectif est de simplifier la recherche, la réservation, et la gestion des séjours de vacances. La liaison transparente entre locataires et propriétaires offre une expérience utilisateur sans accroc.

### Objectifs

- Analyser l'environnement Mockoon.
- Implémenter toutes les routes.
- Mettre en place une authentification JWT via Spring Sécurité.
- Mettre en place la base de données (via docker).
- Documenter avec Swagger.
- Nettoyer le code.

Développé avec Spring Boot, notre backend fournit une base solide pour les fonctionnalités cruciales de cette plateforme.

## Technologies Utilisées

- [Spring Boot](https://spring.io/projects/spring-boot) - Framework Java pour le développement d'applications.

## Prérequis

- [Java 17](https://www.oracle.com/java/)
- [Maven](https://maven.apache.org/)
- [MariaDB Dockerisée](https://mariadb.org/)
- [Docker](https://www.docker.com/)


## Installation

1. Clonez le dépôt : `https://github.com/shiirows/Chatop-backend.git`
2. Accédez au répertoire du projet : `cd Chatop-backend`
3. Compilez le projet : `mvn clean install`

## Création et Lancement de la Base de Données avec Docker

Pour simplifier le processus, utilisez la commande suivante pour créer et lancer la base de données MariaDB à l'aide de Docker Compose :

```bash
docker-compose build
```

Cette commande utilise le fichier `docker-compose.yml` fourni pour créer et configurer la base de données. Assurez-vous que Docker Compose est installé sur votre système.


## Documentation avec Swagger

Explorez l'interface Swagger UI à l'adresse suivante :

[Swagger UI](http://localhost:8080/swagger-ui/)














