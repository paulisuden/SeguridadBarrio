# Usa la imagen base de OpenJDK 17 en una versión ligera
FROM openjdk:21-jdk-slim

# Define la variable ARG para el archivo JAR
ARG JAR_FILE=target/seguridad-barrio-cliente-0.0.1-SNAPSHOT.jar

# Copia el archivo JAR al contenedor
COPY ${JAR_FILE} seguridad-barrio-cliente.jar

# Expone el puerto 8082
EXPOSE 8082

# Define el comando de inicio para ejecutar la aplicación
CMD ["java", "-jar", "seguridad-barrio-cliente.jar"]
