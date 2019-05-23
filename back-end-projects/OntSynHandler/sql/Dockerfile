FROM mysql:5.7
COPY *.sql /docker-entrypoint-initdb.d/
ENV MYSQL_ROOT_PASSWORD=123
ENV MYSQL_DATABASE=explorer