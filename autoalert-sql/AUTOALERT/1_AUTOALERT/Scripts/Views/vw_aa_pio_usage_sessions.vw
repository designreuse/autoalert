create or replace force view vw_aa_pio_usage_sessions as
select ss.spid,
       ss.sid,
       ss.SERIAL#,
       st.value,
       ss.SQL_TEXT,
       ss.USERNAME,
       ss.TERMINAL,
       ss.PROGRAM,
       ss.MODULE,
       ss.MACHINE,
       ss.LOGON_TIME
  from (select a.sid, sum(a.value) as value
          from (select t.SID, n.NAME, t.VALUE
                  from v$sesstat t, v$statname n
                 where t.STATISTIC# = n.STATISTIC#
                   and (upper(n.NAME) like 'PHYSICAL READS%' or
                       upper(n.name) like 'PHYSICAL WRITES%')) a
         group by a.sid) st,
       vw_aa_all_sessions ss
 where ss.sid = st.sid
   and ss.type = 'USER'
   and ss.STATUS = 'ACTIVE'
/


