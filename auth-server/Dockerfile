# escape=`
FROM openjdk:12-oracle

WORKDIR /usr/auth-server

COPY /build/libs/auth-server-*.jar ./auth-server.jar

EXPOSE 8090
ENTRYPOINT ["java", `
            "-jar", `
            "-Djava.security.egd=file:/dev/./urandom", `
            "-Dspring.profiles.active=docker", `
            "auth-server.jar"]
