# escape=`
FROM openjdk:12-oracle

WORKDIR /usr/auth-server

COPY /build/libs/web-server-*.jar ./web-server.jar

EXPOSE 8090
ENTRYPOINT ["java", `
            "-jar", `
            "-Djava.security.egd=file:/dev/./urandom", `
            "-Dspring.profiles.active=docker", `
            "web-server.jar"]
