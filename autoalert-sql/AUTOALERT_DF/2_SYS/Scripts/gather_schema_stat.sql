BEGIN
  dbms_stats.gather_schema_stats(ownname => 'AUTOALERT', estimate_percent => 30, degree => 2, cascade => TRUE);
END;
/

