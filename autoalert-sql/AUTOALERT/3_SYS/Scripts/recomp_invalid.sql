PROMPT ===============================
PROMPT == recompile invalid objects ==
PROMPT ===============================

exec utl_recomp.recomp_parallel(schema => 'AUTOALERT');

PROMPT 'Number of invalid objects: '

SELECT d.owner || ' : ' || COUNT(*) AS errors
  FROM dba_objects d
 WHERE d.owner = 'AUTOALERT'
   AND d.status <> 'VALID'
 GROUP BY d.owner;

