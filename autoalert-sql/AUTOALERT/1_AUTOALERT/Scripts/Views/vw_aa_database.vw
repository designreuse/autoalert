create or replace force view vw_aa_database as
select "DBID",
       "NAME",
       "DB_UNIQUE_NAME",
       "CREATED",
       "RESETLOGS_TIME",
       "LOG_MODE",
       "CONTROLFILE_TYPE",
       "CONTROLFILE_CREATED",
       "OPEN_MODE",
       "DATABASE_ROLE",
       "PLATFORM_NAME",
       "CURRENT_SCN",
       "FLASHBACK_ON"
  from v$database;

