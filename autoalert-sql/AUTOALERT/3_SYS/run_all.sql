col rlog new_value v_log
set define on
set timi off
set time off

set head off
select SYS_CONTEXT('USERENV', 'DB_NAME')||'-'||SYS_CONTEXT('USERENV', 'SESSION_USER')||'_'||to_char(sysdate,'YYYYMMDDHH24MI') rlog FROM dual;
set head on

spool ./AUTOALERT/3_SYS/_Install_Logs/run_all_&v_log..log

set define off
set serveroutput on

show user

@ ./AUTOALERT/3_SYS/Scripts/grants.sql
@ ./AUTOALERT/3_SYS/Scripts/recomp_invalid.sql

spool off

