--------------------------------------------------------------------------------
--
-- Tablespace creation SQL+ script
--
-- Usage:
-- SQL> @create_tablespaces <Datafile path>
--
-- Last modifier:	lcsontos
-- Last modified:	2010-03-04
--
--------------------------------------------------------------------------------

WHENEVER SQLERROR EXIT SQL.SQLCODE FAILURE ROLLBACK
WHENEVER OSERROR  EXIT FAILURE ROLLBACK 

SPOOL create_tablespaces.log

prompt ##
prompt ## Creating tablespace AUTOALERT_DATA ...
prompt ##
CREATE TABLESPACE AUTOALERT_DATA DATAFILE 
  '&1/autoalert_data_01.dbf' SIZE 128M AUTOEXTEND ON NEXT 128M MAXSIZE 4096M
LOGGING
EXTENT MANAGEMENT LOCAL AUTOALLOCATE
SEGMENT SPACE MANAGEMENT AUTO;

prompt ##
prompt ## Creating tablespace AUTOALERT_IDX ...
prompt ##
CREATE TABLESPACE AUTOALERT_IDX DATAFILE 
  '&1/autoalert_idx_01.dbf' SIZE 128M AUTOEXTEND ON NEXT 128M MAXSIZE 4096M
LOGGING
EXTENT MANAGEMENT LOCAL AUTOALLOCATE
SEGMENT SPACE MANAGEMENT AUTO;

SPOOL OFF

