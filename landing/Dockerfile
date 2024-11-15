# Usa la imagen base de OpenJDK 17 en una versión ligera
FROM openjdk:21-jdk-slim

WORKDIR /app

# Define la variable ARG para el archivo JAR
ARG JAR_FILE=target/landing-0.0.1-SNAPSHOT.jar

# Copia el archivo JAR al contenedor
COPY ${JAR_FILE} landing.jar

# Expone el puerto 8083
EXPOSE 8083

# Define el comando de inicio para ejecutar la aplicación
CMD ["java", "-jar", "landing.jar"]
