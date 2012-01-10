create or replace force view vw_aa_database as
select d.dbid,
       d.name,
       d.db_unique_name,
       d.created,
       d.resetlogs_time,
       d.log_mode,
       d.controlfile_type,
       d.controlfile_created,
       d.open_mode,
       d.database_role,
       d.platform_name,
       d.current_scn,
       decode(d.flashback_on, 'YES', 1, 0) as flashback_on,
       i.host_name,
       i.version,
       v.banner,
       i.startup_time,
       decode(i.logins, 'ALLOWED', 1, 0) as logins_allowed,
       decode(i.shutdown_pending, 'YES', 1, 0) as shutdown_pending
  from v$database d,
       v$instance i,
       v$version v
 where upper(v.banner) like 'ORACLE%';
