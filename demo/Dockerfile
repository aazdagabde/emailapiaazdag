# Étape 1 : Construction (build)
FROM gradle:7.6.1-jdk17-alpine AS build

# Passer root pour configurer les permissions
USER root
RUN mkdir -p /home/gradle/app && chown -R gradle:gradle /home/gradle

# Revenir à l’utilisateur gradle
USER gradle
WORKDIR /home/gradle/app

# Copier le code source (build.gradle, settings.gradle, src/...) 
# avec les droits pour 'gradle'
COPY --chown=gradle:gradle . /home/gradle/app

# Construire (désactivation des tests pour aller plus vite, facultatif)
RUN gradle build -x test --no-daemon --stacktrace

# Étape 2 : Image de runtime (JRE seulement)
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copier le JAR généré depuis l’étape précédente
# Vérifie le nom réel dans build/libs après le build (par ex. pf-v1.jar)
COPY --from=build /home/gradle/app/build/libs/demo-v1.jar app.jar

EXPOSE 8080

# Lancer l’appli Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
