#!/bin/bash
psql_host=$1
psql_port=$2
db_base=$3
psql_user=$4
psql_password=$5

lscpu_out=`lscpu`
proc_mem_out=$(cat /proc/meminfo)

#hardware specification info
hostname=$(hostname -f)
cpu_number=$(echo "$lscpu_out" | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$lscpu_out" | egrep "^Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out" | egrep "Model name:" | awk '{print $3,$4,$5,$6,$7}' | xargs)
cpu_mhz=$(echo "$lscpu_out" | egrep "CPU MHz:" | awk '{print $3}' | xargs)
l2_cache=$(echo "$lscpu_out" | egrep "L2 cache:" | awk '{print substr($3,1,3)}' | xargs)
total_mem=$(echo "$proc_mem_out" | egrep "^MemTotal:" | awk '{print $2}' | xargs)
timestamp=$(echo "'$(date "+%F %T")'")

#Insert Statement
insert_stmt="INSERT INTO host_info (
            id, hostname, cpu_number, cpu_architecture,
            cpu_model, cpu_mhz, L2_cache, total_mem,
            "timestamp"
            )
            VALUES
            (
              DEFAULT, '$hostname', $cpu_number,
              '$cpu_architecture', '$cpu_model',
              $cpu_mhz, $l2_cache, $total_mem,
              $timestamp
            );"

psql -h "$psql_host" -p "$psql_port" -U $psql_user -d $db_base -c "$insert_stmt"

exit $?