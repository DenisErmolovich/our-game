# escape=`
FROM openjdk:12-oracle

WORKDIR /usr/game-server

COPY /build/libs/game-server-*.jar ./game-server.jar

EXPOSE 8091
ENTRYPOINT ["java", `
            "-jar", `
            "-Djava.security.egd=file:/dev/./urandom", `
            "game-server.jar"]
