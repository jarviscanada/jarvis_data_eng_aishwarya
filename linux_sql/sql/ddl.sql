\c host_agent;
CREATE TABLE IF NOT EXISTS host_info (
  id SERIAL NOT NULL PRIMARY KEY, hostname VARCHAR NOT NULL UNIQUE,
  cpu_number INT NOT NULL, cpu_architecture VARCHAR NOT NULL,
  cpu_model VARCHAR NOT NULL, cpu_mhz NUMERIC NOT NULL,
  L2_cache INT NOT NULL, total_mem INT NOT NULL,
  "timestamp" TIMESTAMP NOT NULL
);
INSERT INTO host_info (
  id, hostname, cpu_number, cpu_architecture,
  cpu_model, cpu_mhz, L2_cache, total_mem,
  "timestamp"
)
VALUES
  (
    3, 'node1.jrvs.ca', 1, 'x86_64', 'Intel(R) Xeon(R) CPU @ 2.30GHz',
    2300.000, 256, 601324, '2012-05-16 10:01:01'
  ),
  (
    4, 'node2.jrvs.ca', 2, 'x86_64', 'Intel(R) Xeon(R) CPU @ 2.30GHz',
    2300.000, 256, 701324, '2020-05-16 12:01:03'
  );
CREATE TABLE IF NOT EXISTS host_usage (
  "timestamp" TIMESTAMP NOT NULL,
  host_id SERIAL NOT NULL,
  memory_free INT NOT NULL,
  cpu_idle INT NOT NULL,
  cpu_kernel INT NOT NULL,
  disk_io INT NOT NULL,
  disk_available INT NOT NULL,
  FOREIGN KEY (host_id) REFERENCES host_info(id)
);
INSERT INTO host_usage (
  "timestamp", host_id, memory_free,
  cpu_idle, cpu_kernel, disk_io, disk_available
)
VALUES
  (
    '2020-05-16 10:13:28', 3, 256, 95,
    0, 0, 31220
  ),
  (
    '2020-05-16 10:14:28', 3, 250, 95,
    0, 0, 31220
  ),
  (
    '2020-05-16 10:15:28', 3, 2567, 95,
    0, 0, 31220
  ),
  (
    '2020-05-16 10:16:28', 3, 2564, 95,
    0, 0, 31220
  ),
  (
    '2020-05-16 10:17:28', 3, 2562, 95,
    0, 0, 31220
  ),
  (
    '2020-05-16 10:18:28', 3, 2569, 95,
    0, 0, 31220
  ),
  (
    '2020-05-16 10:19:28', 3, 2560, 95,
    0, 0, 31220
  ),
  (
    '2020-05-16 10:20:28', 3, 2567, 95,
    0, 0, 31220
  ),
  (
    '2020-05-16 10:21:28', 3, 2567, 95,
    0, 0, 31220
  ),
  (
    '2020-05-16 10:22:28', 3, 2567, 95,
    0, 0, 31220
  ),
  (
    '2020-05-16 10:23:28', 3, 2567, 95,
    0, 0, 31220
  ),
  (
    '2020-05-16 10:24:28', 3, 2567, 95,
    0, 0, 31220
  ),
  (
    '2020-05-16 10:25:28', 3, 2567, 95,
    0, 0, 31220
  ),
  (
    '2020-05-16 10:26:28', 3, 2567, 95,
    0, 0, 31220
  ),
  (
    '2020-05-16 10:27:28', 3, 2567, 95,
    0, 0, 31220
  ),
  (
    '2020-05-16 12:20:28', 4, 256, 95,
    0, 0, 31220
  ),
  (
    '2020-05-16 12:21:28', 4, 256, 95,
    0, 0, 31220
  ),
  (
    '2020-05-16 12:22:28', 4, 250, 95,
    0, 0, 31220
  ),
  (
    '2020-05-16 12:23:28', 4, 256, 95,
    0, 0, 31220
  ),
  (
    '2020-05-16 12:24:28', 4, 256, 95,
    0, 0, 31220
  ),
  (
    '2020-05-16 12:25:28', 4, 256, 95,
    0, 0, 31220
  ),
  (
    '2020-05-16 12:26:28', 4, 251, 95,
    0, 0, 31220
  ),
  (
    '2020-05-16 12:27:28', 4, 252, 95,
    0, 0, 31220
  ),
  (
    '2020-05-16 12:28:28', 4, 253, 95,
    0, 0, 31220
  ),
  (
    '2020-05-16 12:29:28', 4, 254, 95,
    0, 0, 31220
  ),
  (
    '2020-05-16 12:30:28', 4, 256, 95,
    0, 0, 31220
  );