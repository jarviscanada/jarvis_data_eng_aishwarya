SELECT
  info.cpu_number,
  us.host_id,
  info.total_mem
FROM
  host_info info
  INNER JOIN host_usage us ON us.host_id = info.id
GROUP by
  info.cpu_number,
  us.host_id,
  info.total_mem
ORDER by
  info.cpu_number ASC,
  info.total_mem DESC;
SELECT
  count(*),
  host_id,
  info.hostname,
  date_trunc('hour', us."timestamp")+ date_part('minute', us."timestamp"):: int / 5 * interval '5 min' as time_stamp,
  CAST(
    AVG(info.total_mem - us.memory_free)/ info.total_mem * 100 as decimal (10, 2)
  ) as avg_used_mem_percentage
FROM
  host_usage us
  INNER JOIN host_info info ON info.id = us.host_id
GROUP BY
  host_id,
  info.hostname,
  time_stamp,
  info.total_mem
ORDER BY
  host_id,
  time_stamp ASC;