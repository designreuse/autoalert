REM INSERTING into AA_PARAMETERS
Insert into AA_PARAMETERS (PARAM_NAME,PARAM_DESC,PARAM_TYPE,PARAM_NUM_VALUE,PARAM_VCHAR_VALUE) values ('AUTOALERT_MAIL_FROM','Sender of automatic alerts','V',null,'autoalert@dezso.lan');
Insert into AA_PARAMETERS (PARAM_NAME,PARAM_DESC,PARAM_TYPE,PARAM_NUM_VALUE,PARAM_VCHAR_VALUE) values ('AUTOALERT_RCPT_TO','Recipients of automatic alerts (coma separated list)','V',null,'lcsontos@dezso.lan');
Insert into AA_PARAMETERS (PARAM_NAME,PARAM_DESC,PARAM_TYPE,PARAM_NUM_VALUE,PARAM_VCHAR_VALUE) values ('AUTOALERT_SUBJECT','Subject of the automatically generated alert message','V',null,'Automatic Alert');
Insert into AA_PARAMETERS (PARAM_NAME,PARAM_DESC,PARAM_TYPE,PARAM_NUM_VALUE,PARAM_VCHAR_VALUE) values ('CPU_USAGE_THRESHOLD','CPU usage percentage threshold','N',80,null);
Insert into AA_PARAMETERS (PARAM_NAME,PARAM_DESC,PARAM_TYPE,PARAM_NUM_VALUE,PARAM_VCHAR_VALUE) values ('PIO_USAGE_THRESHOLD','Number of physical R/W-s in a 5min moving window','N',15000,null);
Insert into AA_PARAMETERS (PARAM_NAME,PARAM_DESC,PARAM_TYPE,PARAM_NUM_VALUE,PARAM_VCHAR_VALUE) values ('TBS_SIZERM_THRESHOLD','Tablespace remaining size percentage threshold','N',20,null);
Insert into AA_PARAMETERS (PARAM_NAME,PARAM_DESC,PARAM_TYPE,PARAM_NUM_VALUE,PARAM_VCHAR_VALUE) values ('TBS_USAGE_THRESHOLD','Tablespace usage percentage threshold','N',80,null);
