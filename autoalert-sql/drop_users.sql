set serveroutput on

declare
  v_sql varchar2(250);
begin
  for sss in (
    select s.SID, s.SERIAL#
      from v$session s
     where s.USERNAME like 'AUTOALERT%'
  ) loop
    v_sql := 'alter system kill session ''' || sss.sid || ', ' || sss.serial# || '''';
    dbms_output.put_line('*** ' || v_sql);
    execute immediate v_sql;
    
  end loop;
end;
/

drop user AUTOALERT cascade;
drop user AUTOALERT_JDBC cascade;

