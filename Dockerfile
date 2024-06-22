# Usar la imagen oficial de OpenJDK 21 como base
FROM openjdk:21-jdk-slim

# Crear un directorio para la aplicación
WORKDIR /app

# Copiar el archivo WAR de la aplicación al directorio de trabajo
COPY target/VpetsoftApp-0.0.1-SNAPSHOT.war /app/VpetsoftApp.war

# Exponer el puerto en el que la aplicación se ejecutará
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/VpetsoftApp.war"]