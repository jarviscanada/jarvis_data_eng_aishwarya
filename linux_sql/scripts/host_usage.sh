#!/bin/bash
psql_host=$1
psql_port=$2
db_base=$3
psql_user=$4
psql_password=$5
export PGPASSWORD='password'

proc_mem_out=$(cat /proc/meminfo)

#CPU/Memory info
timestamp=$(echo "'$(date "+%F %T")'")
memory_free=$(echo "$proc_mem_out" | egrep "^MemFree:" | awk '{print $2}' | xargs)
cpu_idle=$(echo "$(vmstat -t)" | tail -1 | awk '{print $15}' | xargs)
cpu_kernel=$(echo "$(vmstat -t)" | tail -1 | awk '{print $14}' | xargs)
disk_io=$(echo  "$(vmstat -d)"| tail -1 | awk '{print $10}' | xargs)
disk_available=$(echo "$(df -BM / )" | tail -1 | awk '{print substr($4,1,length($4)-1)}' | xargs)


#Insert Statement
insert_stmt="INSERT INTO host_usage ("timestamp",host_id,memory_free,cpu_idle, cpu_kernel, disk_io, disk_available)
             SELECT
                $timestamp,
                (
                  SELECT
                      id
                  FROM
                      host_info
                  WHERE
                      hostname='jrvs-remote-desktop-centos7.us-east1-c.c.genuine-orb-276313.internal'
                ),
                $memory_free,
                $cpu_idle,
                $cpu_kernel,
                $disk_io,
                $disk_available;"

psql -h "$psql_host" -p "$psql_port" -U $psql_user -d $db_base -c "$insert_stmt"

exit $?