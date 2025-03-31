# Étape 1 : Construction (Build) avec l’image Gradle
FROM gradle:7.6.1-jdk17-alpine AS build
# Créer un dossier de travail
WORKDIR /app

# Copier le contenu du projet dans l'image
COPY . /app

# Construire l’application (on peut désactiver les tests pour gagner du temps)
RUN gradle build -x test

# Étape 2 : Image de production (runtime)
FROM eclipse-temurin:17-jre-alpine
# Créer un dossier de travail
WORKDIR /app

# Copier le JAR compilé depuis l’étape 'build'
COPY --from=build /app/build/libs/demo-v1.jar app.jar

# Exposer le port 8080 (optionnel, Render utilise aussi $PORT)
EXPOSE 8080

# Commande de lancement
ENTRYPOINT ["java", "-jar", "app.jar"]
