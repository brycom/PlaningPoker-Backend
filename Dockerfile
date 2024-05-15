<<<<<<< Updated upstream
FROM eclipse-temurin:17-jdk-jammy as builder
=======
FROM eclipse-temurin:21-jdk-jammy as builder
>>>>>>> Stashed changes
WORKDIR /opt/app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install -DskipTests

<<<<<<< Updated upstream
FROM eclipse-temurin:17-jre-jammy
WORKDIR /opt/app
EXPOSE 8080
COPY --from=builder /opt/app/target/.jar /opt/app/.jar
=======
FROM eclipse-temurin:21-jre-jammy
WORKDIR /opt/app
EXPOSE 8080
COPY --from=builder /opt/app/target/*.jar /opt/app/*.jar
>>>>>>> Stashed changes
ENTRYPOINT ["java","-Dspring.profiles.active=prod", "-jar", "/opt/app/*.jar" ]