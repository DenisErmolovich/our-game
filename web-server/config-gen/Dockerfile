FROM python:3

WORKDIR /cg/etc/

RUN pip install pyyaml jinja2

COPY . /cg

ENTRYPOINT ["sh", "/cg/docker-entrypoint.sh"]
