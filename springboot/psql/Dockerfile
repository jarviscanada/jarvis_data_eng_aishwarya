FROM postgres:9.6-alpine
COPY ./sql_ddl/init_db.sql /docker-entrypoint-initdb.d/init_db.sql
COPY ./sql_ddl/schema.sql /docker-entrypoint-initdb.d/schema.sql
