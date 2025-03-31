# Étape 1 : Build
FROM gradle:7.6.1-jdk17-alpine AS build

# On switch en root provisoirement pour créer/réassigner les permissions
USER root

# Créer un dossier de travail
RUN mkdir -p /home/gradle/app && chown -R gradle:gradle /home/gradle

# Repasse à l’utilisateur gradle
USER gradle
WORKDIR /home/gradle/app

# Copier le code source avec les droits adéquats
COPY --chown=gradle:gradle . /home/gradle/app

# Construire l'application
# --no-daemon pour éviter les soucis de daemon si on veut plus de logs
# --stacktrace pour avoir un diagnostic précis en cas de problème
RUN gradle build -x test --no-daemon --stacktrace

# Étape 2 : Image de runtime
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copier le jar généré depuis la phase de build
# Vérifie le nom du jar dans "build/libs" après compilation
COPY --from=build /home/gradle/app/build/libs/demo-v1.jar app.jar

# Exposer le port 8080 (optionnel, Render injectera aussi $PORT)
EXPOSE 8080

# Lancer l’appli Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
