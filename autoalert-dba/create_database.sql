--------------------------------------------------------------------------------
--
-- Database creation SQL+ script
--
-- Usage:
-- SQL> @create_database <SIDNAME> <SYS(TEM) password> <Datafile path>
--
-- Last modifier:	lcsontos
-- Last modified:	2010-03-04
--
--------------------------------------------------------------------------------

WHENEVER SQLERROR EXIT SQL.SQLCODE FAILURE ROLLBACK
WHENEVER OSERROR  EXIT FAILURE ROLLBACK 

SPOOL create_database_&1..log

prompt ##
prompt ## Creating database server parameter file ...
prompt ##
CREATE SPFILE FROM PFILE;

prompt ##
prompt ## Starting database instance ...
prompt ##
STARTUP NOMOUNT

timing start overall

timing start create_db

prompt ##
prompt ## Creating database &1 ...
prompt ##
CREATE DATABASE &1
   USER SYS IDENTIFIED BY &2
   USER SYSTEM IDENTIFIED BY &2
   --** Log files
   LOGFILE GROUP 1 ('&3/redo0101.log', '&3/redo0102.log') SIZE 512M,
           GROUP 2 ('&3/redo0201.log', '&3/redo0202.log') SIZE 512M
   CHARACTER SET AL32UTF8
   NATIONAL CHARACTER SET AL16UTF16
   --** System tablespace
   DATAFILE '&3/system01.dbf' SIZE 1024M REUSE AUTOEXTEND ON NEXT 128M MAXSIZE 8192M
   EXTENT MANAGEMENT LOCAL
   --** Sysaux tablespace
   SYSAUX DATAFILE '&3/sysaux01.dbf' SIZE 512M REUSE AUTOEXTEND ON NEXT 128M MAXSIZE 8192M
   --** Default tablespace for non-system users
   DEFAULT TABLESPACE users
        DATAFILE '&3/users01.dbf' SIZE 128M REUSE AUTOEXTEND ON NEXT 128M MAXSIZE 8192M
   --** Temporary tablespace
   DEFAULT TEMPORARY TABLESPACE temp
      TEMPFILE  '&3/temp01.dbf' SIZE 128M REUSE AUTOEXTEND ON NEXT 128M  MAXSIZE 8192M,
				'&3/temp02.dbf' SIZE 128M REUSE AUTOEXTEND ON NEXT 128M  MAXSIZE 8192M,
				'&3/temp03.dbf' SIZE 128M REUSE AUTOEXTEND ON NEXT 128M  MAXSIZE 8192M,
				'&3/temp04.dbf' SIZE 128M REUSE AUTOEXTEND ON NEXT 128M  MAXSIZE 8192M
   --** Undo tablespace
   UNDO TABLESPACE undo
      DATAFILE '&3/undo01.dbf' SIZE 128M REUSE AUTOEXTEND ON NEXT 128M MAXSIZE 8192M,
			   '&3/undo02.dbf' SIZE 128M REUSE AUTOEXTEND ON NEXT 128M MAXSIZE 8192M;

timing stop create_db

timing start create_catalog
prompt ##
prompt ## Creating data dictionary ...
prompt ##
prompt ### ORACLE_HOME/rdbms/admin/catalog.sql
@?/rdbms/admin/catalog.sql
timing stop create_catalog

timing start create_catproc
prompt ##
prompt ## Running all scripts required for, or used with PL/SQL ...
prompt ##
prompt ### ORACLE_HOME/rdbms/admin/catproc.sql
@?/rdbms/admin/catproc.sql
timing stop create_catproc

timing start create_catqueue
prompt ##
prompt ## Creating the dictionary objects required for Advanced Queuing ...
prompt ##
prompt ### ORACLE_HOME/rdbms/admin/catqueue.sql
@?/rdbms/admin/catqueue.sql
timing stop create_catqueue

timing start create_octk
prompt ##
prompt ## Creating the Oracle Cryptographic Toolkit package ...
prompt ##
prompt ### ORACLE_HOME/rdbms/admin/catoctk.sql
@?/rdbms/admin/catoctk.sql
timing stop create_octk

timing start create_lock
prompt ##
prompt ## Creating views that can dynamically display lock dependency graphs
prompt ##
prompt ### ORACLE_HOME/rdbms/admin/catblock.sql
@?/rdbms/admin/catblock.sql
timing stop create_lock

timing start create_xmldb
prompt ##
prompt ## Creating XML database ...
prompt ##
prompt ### ORACLE_HOME/rdbms/admin/catqm.sql &2 SYSAUX TEMP
@?/rdbms/admin/catqm.sql &2 SYSAUX TEMP
timing stop create_xmldb

timing start create_text
prompt ##
prompt ## Installing Oracle Text option ...
prompt ##

timing start create_text_catctx
prompt ### ORACLE_HOME/ctx/admin/catctx.sql &2 SYSAUX TEMP NOLOCK
@?/ctx/admin/catctx.sql &2 SYSAUX TEMP NOLOCK
timing stop create_text_catctx

timing start create_text_drdefus
prompt ### ORACLE_HOME/ctx/admin/defaults/drdefus.sql
@?/ctx/admin/defaults/drdefus.sql
timing stop create_text_drdefus

timing stop create_text

timing start create_jvm
prompt ##
prompt ## Installing Oracle JVM option ...
prompt ##

timing start create_jvm_initjvm
prompt ### ORACLE_HOME/javavm/install/initjvm.sql
@?/javavm/install/initjvm.sql
timing stop create_jvm_initjvm

timing start create_jvm_initxml
prompt ### ORACLE_HOME/xdk/admin/initxml.sql
@?/xdk/admin/initxml.sql
timing stop create_jvm_initxml

timing start create_jvm_xmlja
prompt ### ORACLE_HOME/xdk/admin/xmlja.sql
@?/xdk/admin/xmlja.sql
timing stop create_jvm_xmlja

timing start create_jvm_catjava
prompt ### ORACLE_HOME/rdbms/admin/catjava.sql
@?/rdbms/admin/catjava.sql
timing stop create_jvm_catjava

timing start create_jvm_catexf
prompt ### ORACLE_HOME/rdbms/admin/catexf.sql
@?/rdbms/admin/catexf.sql
timing stop create_jvm_catexf

timing stop create_jvm

timing start create_owa
prompt ##
prompt ## Installing OWA packages ...
prompt ##
prompt ### ORACLE_HOME/rdbms/admin/owainst.sql
@?/rdbms/admin/owainst.sql
timing stop create_owa

timing start create_mail
prompt ##
prompt ## Installing UTL_MAIL packages ...
prompt ##

timing start create_mail_utlmail
prompt ### ORACLE_HOME/rdbms/admin/utlmail.sql
@?/rdbms/admin/utlmail.sql
timing stop create_mail_utlmail

timing start create_mail_prvtmail
prompt ### ORACLE_HOME/rdbms/admin/prvtmail.plb
@?/rdbms/admin/prvtmail.plb
timing stop create_mail_prvtmail

timing stop create_mail

timing start recompile
prompt ##
prompt ## Recompiling ivalid packages ...
prompt ##
prompt ### ORACLE_HOME/rdbms/admin/utlrp.sql
@?/rdbms/admin/utlrp.sql
timing stop recompile

timing stop overall

SPOOL OFF
