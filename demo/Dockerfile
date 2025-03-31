# Étape 1 : Build
FROM eclipse-temurin:17-jdk-alpine AS build
# (ou gradle:7.6.1-jdk17-alpine, mais si tu as la wrapper c'est facultatif)

WORKDIR /app

# Copier seulement les fichiers nécessaires au wrapper et à la config
COPY gradlew /app/
COPY gradle /app/gradle
COPY build.gradle settings.gradle /app/
COPY src /app/src

# Rendre gradlew exécutable
RUN chmod +x /app/gradlew

# Supprimer le cache pour être certain de retélécharger
RUN rm -rf /root/.gradle/caches

# Lancer la build via ./gradlew
RUN ./gradlew build -x test --no-daemon --stacktrace

# Étape 2 : Runtime
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/demo-v1.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
