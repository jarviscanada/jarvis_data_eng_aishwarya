# Hadoop Project
Table of contents
* [Introduction](#Introduction)
* [Hadoop Cluster](#Hadoop-Cluster)
* [Hive Project](#Hive-Project)
* [Improvements](#Improvements) 

# Introduction
The purpose of the Hadoop project is to process Big Data using big data platforms. The focus is on processing big data using Apache Hadoop and evaluate different tools to help the data analytics team. In this project, provisioned a Hadoop Cluster using GCP, evaluated core Hadoop components such as MapReduce, HDFC and YARN and solved a business problem using Apache Hive and Zeppelin Notebook. In Hive Project, dealt with parsing issues, Hive partitions, Columnar File Optimization, executed queries for Highest GDP Growth and sorting operations.

# Hadoop Cluster
- Cluster Architecture Diagram
 ![Cluster Architecture](./assets/Cluster_Architecture.png)
  - The cluster consists of a single MasterNode and two worker nodes.
  - HDFS stands for Hadoop Distributed FileSystem.  A filesystem that manages the storage across a network of machines is called distributed filesystems.
  - MapReduce - MapReduce is a programming model. Although it's not intuitive, it's very suitable for processing and generating big data sets with a parallel, distributed algorithm on a cluster. 
  - YARN is Yet Another Resource Negotiator that separates computational resources and processes. YARN offers clear advantages in scalability, efficiency, and flexibility compared to the classical MapReduce engine in the first version of Hadoop.
  - Zeppelin - Zeppelin notebook gives you an easy, straightforward way to execute arbitrary code in a web notebook. You can execute Scala, SQL, and even schedule a job (via cron) to run at a regular interval. Zeppelin supports Spark, PySpark, Spark R, Spark SQL.
  - Hive (hive Server, Hive Metastore, RDBMS) - Hive will convert/translate HQL to MapReduce jobs, which process data on HDFS (so Hive doesn't store any data files). Nowadays, the Hive can convert SQL to other execution engines, such as Tez and Spark. Furthermore, Hive does manage metadata in an RDBMS of your choice. Hive metadata is very similar to RDBMS DDL, such database name, table name, column data type, and HDFS file location.
- hardware specifications: All nodes have 2 CPUs and 13 GB Memory along with disk space of 100 GB.
- Queries written in Zeppelin connects to Hive server using JDBC connections. Hive works with metadata and other hive services to generate distributed applications or task. YARN receives the job submitted by Hive. YARN is responsible for distributing the jobs and coordinating it between different worker nodes.

# Hive Project
The purpose of the Hive Project is to evaluate business problems using Apache Hive and Zeppelin Notebook. In this project, loaded data from google storage to HDFC, monitored YARN jobs, solved parsing issues using openCSVSerde and learned about its limitations. Evaluated queries to find 2015 Canada GDP Growth using HQL, followed by Hive partitions and Columnar File Optimization. Executed queries for Highest GDP Growth and sorting GDP data by country and year.
- Zeppelin Notebook
    ![Zeppelin Notebook](./assets/Zeppelin.png)

# Improvements
1. Partition the data further to optimize the query. Here partition was done based on `year`. Partitioning the same data by country or `indicatorname/code` could be done.
2. Learn and attempt to optimize the SQL queries by using joins.
3. Implementation using Spark SQL for better performance.