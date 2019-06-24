#gateway
Nginx docker container(performs JWT security checks and caching,
serving static files)

##Launch
`docker build --no-cache -t ng .`  
`docker run --rm -p8080:8080 --name ng ng`  
`winpty docker exec -it ng bash`
