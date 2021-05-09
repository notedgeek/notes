FROM maven:3.8-jdk-11 as maven
COPY ./pom.xml ./pom.xml
RUN mvn dependency:go-offline -B

COPY ./src ./src
RUN mvn package -DskipTests

FROM openjdk:11-jre-alpine
WORKDIR /my-project
COPY --from=maven target/notes-0.0.1-SNAPSHOT.jar  ./
CMD ["java", "-jar", "./notes-0.0.1-SNAPSHOT.jar "]
