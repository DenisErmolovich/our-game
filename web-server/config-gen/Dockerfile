# escape=`
FROM python:3
MAINTAINER denisermolovich@gmail.com

WORKDIR /cg/etc/

RUN pip install pyyaml jinja2
COPY . /cg

ENTRYPOINT ["sh", "/cg/docker-entrypoint.sh"]
