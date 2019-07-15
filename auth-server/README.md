# auth-server

## Description
Authentication micro service.

## Build
`.\gradlew bootJar`

## Launch local
* Launch auth-db
* Launch auth-server `.\gradlew bootRun`

## Docker
* `docker build -t auth-server .`
* `docker run -d --rm -p 8090:8090 --name auth-server auth-server`
