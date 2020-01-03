# escape=`
FROM openjdk:13-oracle

WORKDIR /usr/game-server

COPY /application/spring-app/build/libs/spring-app-*.jar ./game-server.jar

EXPOSE 8091
ENTRYPOINT ["java", `
            "-jar", `
            "-Djava.security.egd=file:/dev/./urandom", `
            "-Dspring.profiles.active=docker", `
            "game-server.jar"]
