# Utiliza una imagen base adecuada
FROM maven:3.8.5-openjdk-17 AS build

# Establece el directorio de trabajo en el contenedor
WORKDIR /app

# Copia el archivo pom.xml y descarga las dependencias del proyecto
COPY pom.xml ./
RUN mvn dependency:resolve

# Copia el resto de los archivos del proyecto
COPY . ./

# Compila el proyecto y resuelve las dependencias
RUN mvn clean install -DskipTests

# Utiliza una imagen base para la fase de ejecución
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo en el contenedor
WORKDIR /app

# Copia el archivo WAR generado desde la fase de construcción
COPY --from=build /app/target/VpetsoftApp-0.0.1-SNAPSHOT.war ./app.war

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.war"]