grant select on sys.dba_tablespaces to autoalert with grant option;
grant select on sys.dba_data_files to autoalert with grant option;
grant select on sys.dba_free_space to autoalert with grant option;
grant select on sys.v_$sql to autoalert with grant option;
grant select on sys.v_$process to autoalert with grant option;
grant select on sys.v_$session to autoalert with grant option;
grant select on sys.v_$sysmetric_history to autoalert with grant option;
grant select on sys.v_$sesstat to autoalert with grant option;
grant select on sys.v_$statname to autoalert with grant option;
grant select on sys.v_$database to autoalert with grant option;

grant select on autoalert.vw_aa_all_datafiles to autoalert_jdbc;
grant select on autoalert.vw_aa_all_sessions to autoalert_jdbc;
grant select on autoalert.vw_aa_all_tablespaces to autoalert_jdbc;
grant select on autoalert.vw_aa_cpu_usage_instance to autoalert_jdbc;
grant select on autoalert.vw_aa_cpu_usage_sessions to autoalert_jdbc;
grant select on autoalert.vw_aa_database to autoalert_jdbc;
grant select on autoalert.vw_aa_pio_usage_instance to autoalert_jdbc;
grant select on autoalert.vw_aa_pio_usage_sessions to autoalert_jdbc;

grant select,insert,update,delete on autoalert.aa_parameters to autoalert_jdbc;
