#!/bin/bash
db_username=$2
db_password=$3
systemctl status docker || systemctl start docker
if [ "$1" = "create" ]; then
  if [ $(docker container ls -a -f name=jrvs-psql | wc -l) -eq 2 ]; then
    echo "jrvs-psql container already created!"
    exit 1
  fi

  if [ "$#" -ne 3 ]; then
    echo "Either username or password not given."
    exit 1
  fi

  docker volume create pgdata
  docker run --name jrvs-psql -e POSTGRES_PASSWORD=${db_password} -e POSTGRES_USER=${db_username} -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres
  exit $?
fi

if [ $(docker container ls -a -f name=jrvs-psql | wc -l) -ne 2 ]; then
    echo "jrvs-psql container not created."
    exit 1
fi

if [ "$1" = "start" ]; then
  docker container start jrvs-psql
  exit $?
elif [ "$1" = "stop" ]; then
  docker container stop jrvs-psql
  exit $?
else
  echo "Invalid Action: Expected start,stop or create"
  exit 1
fi

exit 0




