create or replace force view vw_aa_all_sessions as
select p.spid,
       s.sid,
       s.SERIAL# as serial,
       s.STATUS,
       s.TYPE,
       sq.SQL_TEXT,
       s.USERNAME,
       s.TERMINAL,
       s.PROGRAM,
       s.MODULE,
       s.MACHINE,
       s.LOGON_TIME
  from v$session s, v$process p, v$sql sq
 where s.PADDR = p.ADDR
   and s.SQL_ID = sq.SQL_ID(+);
