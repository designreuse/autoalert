WHENEVER SQLERROR EXIT SQL.SQLCODE FAILURE ROLLBACK
WHENEVER OSERROR  EXIT FAILURE ROLLBACK

set define on
set echo off
set verify off

PROMPT # Create users

ACCEPT autoalert_pwd        DEF 'nhz6mju7' PROMPT 'Password for AUTOALERT      user ( default is nhz6mju7 ): '  HIDE
ACCEPT autoalert_jdbc_pwd   DEF 'nhz6mju7' PROMPT 'Password for AUTOALERT_JDBC user ( default is nhz6mju7 ): '  HIDE

-- create users --
@ create_users.sql

--*********************
--** AUTOALERT
--*********************

--  AUTOALERT objects --
set define on
connect AUTOALERT/&autoalert_pwd

prompt @ ./AUTOALERT/1_AUTOALERT/run_all.sql
@ ./AUTOALERT/1_AUTOALERT/run_all.sql

--  AUTOALERT_JDBC objects --
set define on
connect AUTOALERT_JDBC/&autoalert_jdbc_pwd

prompt @ ./AUTOALERT/2_AUTOALERT_JDBC/run_all.sql
@ ./AUTOALERT/2_AUTOALERT_JDBC/run_all.sql


-- SYS --
set define on
connect / as sysdba

prompt @ ./AUTOALERT/3_SYS/run_all.sql
@ ./AUTOALERT/3_SYS/run_all.sql

--*********************
--** AUTOALERT DATAFIX
--*********************

-- AUTOALERT data --
set define on
connect AUTOALERT/&autoalert_pwd

prompt @ ./AUTOALERT_DF/1_AUTOALERT/run_all.sql
@ ./AUTOALERT_DF/1_AUTOALERT/run_all.sql

-- SYS --
set define on
connect / as sysdba

prompt @ ./AUTOALERT_DF/2_SYS/run_all.sql
@ ./AUTOALERT_DF/2_SYS/run_all.sql

UNDEFINE autoalert_pwd
UNDEFINE autoalert_jdbc_pwd
