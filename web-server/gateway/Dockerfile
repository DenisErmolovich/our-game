# escape=`
FROM openresty/openresty:alpine-fat
MAINTAINER denisermolovich@gmail.com

WORKDIR /gateway

RUN /usr/local/openresty/luajit/bin/luarocks install lua-resty-jwt
COPY /web-server/gateway .
COPY /core-ui/dist/core-ui /static/core-ui

EXPOSE 8080
ENTRYPOINT ["sh", "/gateway/docker-entrypoint.sh"]
