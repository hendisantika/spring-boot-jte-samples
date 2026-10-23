# syntax=docker/dockerfile:1

FROM eclipse-temurin:25-jdk-noble AS build
WORKDIR /workspace
COPY .mvn .mvn
COPY mvnw pom.xml ./
RUN --mount=type=cache,target=/root/.m2 ./mvnw -B -ntp dependency:go-offline
COPY src src
RUN --mount=type=cache,target=/root/.m2 ./mvnw -B -ntp -DskipTests package \
    && java -Djarmode=tools -jar target/jte-forms-0.0.1-SNAPSHOT.jar extract --layers --launcher --destination target/extracted

FROM eclipse-temurin:25-jre-noble
WORKDIR /app
RUN groupadd --system spring && useradd --system --gid spring spring
USER spring:spring
COPY --from=build /workspace/target/extracted/dependencies/ ./
COPY --from=build /workspace/target/extracted/spring-boot-loader/ ./
COPY --from=build /workspace/target/extracted/snapshot-dependencies/ ./
COPY --from=build /workspace/target/extracted/application/ ./
ENV SPRING_PROFILES_ACTIVE=prod
EXPOSE 8080
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
