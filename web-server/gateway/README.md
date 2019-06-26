#gateway
Nginx docker container(performs JWT security checks and caching,
serving static files)

##Launch
`docker build -t gateway .`  
`docker run -d --rm -p8080:8080 --name gateway gateway`
