FROM gradle:8.10.2-jdk21 AS build
WORKDIR /home/gradle/project
COPY --chown=gradle:gradle . .
ENV GRADLE_OPTS="-Dorg.gradle.jvmargs=-Xmx384m -Dfile.encoding=UTF-8"
RUN gradle --no-daemon clean bootJar

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /home/gradle/project/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Xms64m","-Xmx256m","-jar","/app/app.jar"]
