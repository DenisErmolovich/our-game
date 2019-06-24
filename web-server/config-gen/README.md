#Nginx config generator container
Generate config for Nginx.  
Place YAML configs to etc/applications and
generate nginx.config file for gateway.

##Launch
`docker build -t config-gen .`  
`docker run --rm config-gen`
