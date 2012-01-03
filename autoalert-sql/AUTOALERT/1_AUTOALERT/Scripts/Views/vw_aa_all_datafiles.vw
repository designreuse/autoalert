create or replace force view vw_aa_all_datafiles as
select g.TABLESPACE_NAME,
       g.FILE_NAME,
       g.AUTOEXT,
       g.INCR_BY_MB,
       g.SIZE_MAX_MB,
       g.SIZE_MB,
       g.USED_MB,
       g.FREE_MB,
       g.SIZE_REMAIN_MB,
       g.USED_PER,
       g.SIZE_REMAIN_PER,
       pu.param as threshold,
       (case when pu.param is not null /*and ps.param is not null*/ then 1 else 0 end) as alert
  from (select f.TABLESPACE_NAME,
               f.FILE_NAME,
               f.AUTOEXT,
               f.INCR_BY_MB,
               f.SIZE_MAX_MB,
               f.SIZE_MB,
               f.USED_MB,
               f.FREE_MB,
               f.SIZE_REMAIN_MB,
               f.USED_PER,
               round(f.size_remain_mb / f.size_max_mb * 100) as size_remain_per
          from (select t.tablespace_name,
                       t.file_name,
                       t.autoext,
                       t.incr_by_mb,
                       decode(t.autoext, 'YES', t.size_max_mb, 'NO', t.size_mb) as size_max_mb,
                       t.size_mb,
                       t.size_mb - free_mb as used_mb,
                       t.free_mb,
                       decode(t.autoext, 'YES',t.size_max_mb - t.size_mb, 'NO', t.free_mb) as size_remain_mb,
                       round((t.size_mb - free_mb) / t.size_mb * 100) as used_per
                  from (select dt.tablespace_name,
                               df.file_name,
                               df.autoextensible as autoext,
                               (df.increment_by * dt.block_size / 1024 / 1024) as incr_by_mb,
                               (df.bytes / 1024 / 1024) as size_mb,
                               (df.maxbytes / 1024 / 1024) as size_max_mb,
                               (dfs.free_bytes / 1024 / 1024) as free_mb
                          from dba_tablespaces dt,
                               dba_data_files df,
                               (select f.tablespace_name,
                                       f.file_id,
                                       sum(f.bytes) as free_bytes
                                  from dba_free_space f
                                 group by f.tablespace_name, f.file_id) dfs
                         where dt.tablespace_name = df.tablespace_name
                           and dt.tablespace_name = dfs.tablespace_name
                           and df.file_id = dfs.file_id) t) f) g,
       (select param_num_value as param
          from aa_parameters t
         where param_name = 'TBS_USAGE_THRESHOLD') pu/*,
       (select param_num_value as param
          from aa_parameters t
         where param_name = 'TBS_SIZERM_THRESHOLD') ps*/
 where g.used_per > pu.param (+)
--   and g.size_remain_per < ps.param (+)
/

