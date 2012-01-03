create or replace force view vw_aa_all_tablespaces as
select ts.TABLESPACE_NAME,
       ts.SIZE_MAX_MB,
       ts.SIZE_MB,
       ts.USED_MB,
       ts.FREE_MB,
       ts.SIZE_REMAIN_MB,
       ts.USED_PER,
       ts.SIZE_REMAIN_PER,
       pu.param as threshold,
       (case when pu.param is not null /*and ps.param is not null */then 1 else 0 end) as alert
  from (select t.TABLESPACE_NAME,
               t.SIZE_MAX_MB,
               t.SIZE_MB,
               t.USED_MB,
               t.FREE_MB,
               t.SIZE_REMAIN_MB,
               round((t.size_mb - free_mb) / t.size_mb * 100) as used_per,
               round(t.size_remain_mb / t.size_max_mb * 100) as size_remain_per
          from (select df.tablespace_name,
                       sum(df.size_max_mb) as size_max_mb,
                       sum(df.size_mb) as size_mb,
                       sum(df.used_mb) as used_mb,
                       sum(df.free_mb) as free_mb,
                       sum(df.size_remain_mb) as size_remain_mb
                  from vw_aa_all_datafiles df
                 group by df.tablespace_name) t) ts,
       (select param_num_value as param
          from aa_parameters t
         where param_name = 'TBS_USAGE_THRESHOLD') pu/*,
       (select param_num_value as param
          from aa_parameters t
         where param_name = 'TBS_SIZERM_THRESHOLD') ps*/
 where ts.used_per > pu.param (+)
   --and ts.size_remain_per < ps.param (+)
/
