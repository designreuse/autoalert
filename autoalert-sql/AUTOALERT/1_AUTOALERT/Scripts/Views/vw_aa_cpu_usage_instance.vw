create or replace force view vw_aa_cpu_usage_instance as
select c.ID,
       decode(c.id, 1, 1, 0) as last_slice,
       c.TIME_SLICE,
       c.SLICE_COUNT,
       c.BEGIN_TIME,
       c.END_TIME,
       c.VALUE,
       c.THRESHOLD,
       c.ALERT
  from (select rank() over(order by u.begin_time desc) as id,
               u.TIME_SLICE,
               u.slice_count,
               u.BEGIN_TIME,
               u.END_TIME,
               u.value,
               p.param as threshold,
               nvl2(p.param, 1, 0) as alert
          from (select t.time_slice,
                       count(distinct t.begin_time) as slice_count,
                       min(t.begin_time) as begin_time,
                       max(t.end_time) as end_time,
                       round(avg(t.value)) as value
                  from (select to_char(smh.BEGIN_TIME, 'yyyymmddhh24') ||
                               lpad(floor(to_char(smh.BEGIN_TIME, 'mi') / 5),
                                    2,
                                    '0') time_slice,
                               smh.BEGIN_TIME,
                               smh.END_TIME,
                               smh.VALUE
                          from v$sysmetric_history smh
                         where smh.GROUP_ID = 2
                           and smh.METRIC_ID = 2057) t
                 group by t.time_slice, 90) u,
               (select param_num_value as param
                  from aa_parameters t
                 where param_name = 'CPU_USAGE_THRESHOLD') p
         where u.value > p.param(+)
           and u.slice_count = 5) c;
