# gateway
Nginx docker container(performs JWT security checks and caching,
serving static files)

## Launch
`docker build -t gateway -f web-server/gateway/Dockerfile .` from project root dir  
`docker run -d --rm -p8080:8080 --name gateway gateway`
