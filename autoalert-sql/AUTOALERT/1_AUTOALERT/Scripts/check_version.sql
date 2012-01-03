prompt ## VERSION

-- Create table
create table VERSION
(
  VID      NUMBER(6) not null,
  VERSION  VARCHAR2(8) not null,
  RLS_DESC VARCHAR2(100),
  RLS_FLAG CHAR(1) default 'R' not null,
  RLS_TIME DATE default sysdate not null
)
tablespace AUTOALERT_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table VERSION
  is 'This table helps to track the version of the framework.';
-- Add comments to the columns 
comment on column VERSION.VID
  is 'Version ID';
comment on column VERSION.VERSION
  is 'Dot separated string of version';
comment on column VERSION.RLS_DESC
  is 'Release description';
comment on column VERSION.RLS_FLAG
  is 'R: Release, P: Patch, D: Datafix';
comment on column VERSION.RLS_TIME
  is 'Date of deployment';
-- Create/Recreate primary, unique and foreign key constraints 
alter table VERSION
  add constraint VERSION_PK primary key (VID)
  using index 
  tablespace AUTOALERT_IDX
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

alter table VERSION
  add constraint VERSION_UK unique (VERSION)
  using index 
  tablespace AUTOALERT_IDX
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
  
--** Checker procedure
prompt ## CHECK_APP_VERSION
create or replace procedure CHECK_APP_VERSION( p_vid in number default 0,
                                               p_app in varchar2) is
  v_num number;
begin
  v_num := 1;
  select count(*) into v_num
    from version v
   where v.vid >= p_vid;
   
  if v_num > 0 then
    raise_application_error(-20099, 'The version number of this component is higher or equal than that one you are trying to install: ' || p_app );
  end if;
end CHECK_APP_VERSION;
/

--** Execute version check
prompt ## Checking currently installed version ...
begin
  check_app_version('10000', 'AUTOALERT');
end;
/

--** Register inital install of the system
insert into version (vid, version, rls_desc, rls_flag, rls_time)
values (10000, '1.0.0', 'Auto Alert Application V1', 'R', sysdate);
commit;

