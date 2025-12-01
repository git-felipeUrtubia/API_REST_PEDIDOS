# Etapa 1: Compilar el proyecto
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copiar el pom y el código fuente
COPY pom.xml .
COPY src ./src

# Construir la aplicación (jar)
RUN mvn clean package -DskipTests

# Etapa 2: Imagen más ligera para ejecutar la app
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# Copiar el jar generado desde la etapa de build
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto 8080
EXPOSE 8080

# Comando de inicio
CMD ["java", "-jar", "app.jar"]
