PROMPT Create AUTOALERT user

create user AUTOALERT
  identified by &autoalert_pwd
  default tablespace AUTOALERT_DATA
  temporary tablespace TEMP
  profile DEFAULT
  quota unlimited on AUTOALERT_DATA;
-- Grant/Revoke role privileges 
grant connect to AUTOALERT;
grant resource to AUTOALERT;
grant select_catalog_role to AUTOALERT;
-- Grant/Revoke system privileges 
grant alter session to AUTOALERT;
grant create synonym to AUTOALERT;
grant create view to AUTOALERT;
grant debug any procedure to AUTOALERT;
grant debug connect session to AUTOALERT;
grant select any dictionary to AUTOALERT;
grant unlimited tablespace to AUTOALERT;

PROMPT Create AUTOALERT_JDBC user

create user AUTOALERT_JDBC
  identified by &autoalert_jdbc_pwd
  default tablespace AUTOALERT_DATA
  temporary tablespace TEMP
  profile DEFAULT
  quota unlimited on AUTOALERT_DATA;
-- Grant/Revoke role privileges 
grant connect to AUTOALERT_JDBC;
grant resource to AUTOALERT_JDBC;
-- Grant/Revoke system privileges 
grant create synonym to AUTOALERT_JDBC;

