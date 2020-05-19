# Linux Cluster Monitoring Agent
This project is under development. Since this project follows the GitFlow, the final work will be merged into the master branch after the Team Code Team.

## Introduction
Cluster Monitor Agent is an internal tool that monitors the cluster resources usage (here focusing on CPU and Memory usages) in realtime and stores in an RDBMS Database. We have used Docker to create a container for psql instance which contains the database. In every 1 minute, the usage information is fed to the database. It helps the infrastructure team to generate some reports for future resource planning purposes such as addition or removal of servers. For instance, if the usage of the server has a higher percentage, then more node/server/host would be required to distribute the load efficiently.

## Architecture and Design
1) Cluster Diagram:

   ![Cluster Diagram](./assets/Cluster-Page-2.png)
   
2) Two tables described in SQL:
   * One dedicated for storing hardware specification of the host (host_info) and which will execute only once when installed.
   * The other table is dedicated to storing the usage data information of the host (host_usage). The aim is to update every minute.
3) Bash scripts: 
   * Instantiating Docker with psql instance - `psql_docker.sh`
   * Acquiring host specification and inserting to the database - `host_info.sh`
    * Acquiring usage data and inserting to the database - `host_usgae.sh`

## Usage
1) Defined host_info table and host_usage table in `ddl.sql` with all necessary constraints.
2) `host_info.sh` is used to collect hardware specifications of the host and insert to the database using a psql instance. It will run once at the time of installation.
3) `host_usage.sh` is used to collect usage information (CPU and Memory) of the host and insert to the database
4) crontab setup is used to trigger `host_usage.sh` for every 1 minute and acquire usage information of the host.
5) Lastly, `sql_queries` for giving information regarding total memory per host based on the number of CPU. Along with average used memory percentage over 5 minutes interval for each host.

## Improvements 
1) Handle hardware update 
2) Check node failure
3) Efficient coding approaches