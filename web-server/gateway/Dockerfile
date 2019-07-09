FROM openresty/openresty:alpine-fat

WORKDIR /gateway

RUN /usr/local/openresty/luajit/bin/luarocks install lua-resty-jwt

EXPOSE 8080

COPY /web-server/gateway .
COPY /core-ui/dist/core-ui /static/core-ui

ENTRYPOINT ["sh", "/gateway/docker-entrypoint.sh"]
