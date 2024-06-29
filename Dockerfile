# Utilizar la imagen base de OpenJDK
FROM openjdk:21-jdk-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR de la aplicación
COPY target/asistencia.jar asistencia.jar

# Exponer el puerto que utilizará la aplicación Spring Boot
EXPOSE 8080

# Comando para ejecutar la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "asistencia.jar"]
