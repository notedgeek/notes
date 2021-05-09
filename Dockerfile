FROM maven:3.8-jdk-11 as maven
COPY ./pom.xml ./pom.xml
RUN mvn dependency:go-offline -B

COPY ./src ./src
RUN mvn package -DskipTests

FROM adoptopenjdk/openjdk11:jdk-11.0.9.1_1
WORKDIR /my-project
COPY --from=maven target/notes-0.0.1-SNAPSHOT.jar  ./
CMD ["java", "-jar", "./notes-0.0.1-SNAPSHOT.jar"]
