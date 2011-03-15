
-- ----------------------------
-- Table structure for t_dailywork
-- ----------------------------
CREATE TABLE `t_dailywork` (
  `id` varchar(20) character set utf8 collate utf8_general_ci NOT NULL,
  `title` varchar(100) character set utf8 collate utf8_general_ci default NULL,
  `dailyworktype` varchar(20) character set utf8 collate utf8_general_ci default NULL COMMENT '字典表中增加 日常工作类别={由用户自定义}            本表中填入其值。',
  `missiongrade` varchar(10) character set utf8 collate utf8_general_ci default NULL COMMENT '字典表中增加  任务级别={日常任务、紧急任务、特殊任务}            在本表中，直接填入任务级别值。',
  `attachment` varchar(100) character set utf8 collate utf8_general_ci default '' COMMENT '上传文件，此字段记录文件所在相对目录及文件名',
  `target` varchar(20) character set utf8 collate utf8_general_ci default '' COMMENT '字典表中定义 工作指标={日、周、月}            本表中填入值',
  `targetvalue` smallint(6) default NULL COMMENT '手工录入，只能是整数',
  `feedbackpattern` varchar(20) character set utf8 collate utf8_general_ci default NULL COMMENT '关键反馈单的ID编号',
  `makedatetime` datetime default NULL,
  `maker` varchar(20) character set utf8 collate utf8_general_ci default NULL,
  `remark` varchar(200) character set utf8 collate utf8_general_ci default NULL,
  `executors` text character set utf8 collate utf8_general_ci COMMENT '选取各部门中的员工，员工之间以半角逗号分隔，最后一个员工不加逗号，如”张三,李四,王五“',
  `state` varchar(10) character set utf8 collate utf8_general_ci default NULL COMMENT '新任务、已审核、处理中、已中止、已完成、已归档            除了新任务外，其他状态不允许可删除任务单,已审核状态执行人手机就可收到任务，执行人读取后返回处理中，任务可以由创建人标记已中止，已完成，已归档',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日常工作表';

-- ----------------------------
-- Table structure for t_dailyworkexcute
-- ----------------------------
CREATE TABLE `t_dailyworkexcute` (
  `id` varchar(20) character set utf8 collate utf8_general_ci default NULL,
  `pid` varchar(20) character set utf8 collate utf8_general_ci default NULL,
  `executorid` varchar(20) character set utf8 collate utf8_general_ci default NULL,
  `status` smallint(6) default NULL COMMENT '处理中=0，已完成=1，已评份=9',
  `score` int(11) default NULL,
  `remark` varchar(200) character set utf8 collate utf8_general_ci default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='执行人列表';

-- ----------------------------
-- Table structure for t_declare
-- ----------------------------
CREATE TABLE `t_declare` (
  `id` varchar(20) character set utf8 collate utf8_general_ci NOT NULL,
  `title` varchar(100) character set utf8 collate utf8_general_ci default NULL,
  `declaretype` varchar(20) character set utf8 collate utf8_general_ci default NULL COMMENT '字典表中增加 日常工作类别={由用户自定义}            本表中填入其值。',
  `missiongrade` varchar(10) character set utf8 collate utf8_general_ci default NULL COMMENT '字典表中增加  任务级别={日常任务、紧急任务、特殊任务}            在本表中，直接填入任务级别值。',
  `attachment` varchar(100) character set utf8 collate utf8_general_ci default NULL COMMENT '上传文件，此字段记录文件所在相对目录及文件名',
  `target` varchar(20) character set utf8 collate utf8_general_ci default NULL COMMENT '字典表中定义 工作指标={日、周、月}            本表中填入值',
  `targetvalue` smallint(6) default NULL COMMENT '手工录入，只能是整数',
  `declarepattern` varchar(20) character set utf8 collate utf8_general_ci default NULL COMMENT '关键反馈单的ID编号',
  `makedatetime` datetime default NULL,
  `maker` varchar(20) character set utf8 collate utf8_general_ci default NULL,
  `remark` varchar(200) character set utf8 collate utf8_general_ci default NULL,
  `executors` text character set utf8 collate utf8_general_ci COMMENT '选取各部门中的员工，员工之间以半角逗号分隔，最后一个员工不加逗号，如”张三,李四,王五“',
  `state` varchar(10) character set utf8 collate utf8_general_ci default NULL COMMENT '新任务、已审核、处理中、已中止、已完成、已归档            除了新任务外，其他状态不允许可删除任务单,已审核状态执行人手机就可收到任务，执行人读取后返回处理中，任务可以由创建人标记已中止，已完成，已归档',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='事项申报';

-- ----------------------------
-- Table structure for t_declareexcute
-- ----------------------------
CREATE TABLE `t_declareexcute` (
  `id` varchar(20) character set utf8 collate utf8_general_ci default NULL,
  `pid` varchar(20) character set utf8 collate utf8_general_ci default NULL,
  `executorid` varchar(20) character set utf8 collate utf8_general_ci default NULL,
  `status` smallint(6) default NULL COMMENT '处理中=0，已完成=1，已评份=9',
  `score` int(11) default NULL,
  `remark` varchar(200) character set utf8 collate utf8_general_ci default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='执行人列表';

-- ----------------------------
-- Table structure for t_dictionary
-- ----------------------------
CREATE TABLE `t_dictionary` (
  `Id` varchar(20) character set utf8 collate utf8_general_ci NOT NULL,
  `Name` varchar(50) character set utf8 collate utf8_general_ci default NULL,
  `Remark` varchar(100) character set utf8 collate utf8_general_ci default '',
  `IsSys` smallint(6) default '0',
  `SysDictId` varchar(20) character set utf8 collate utf8_general_ci default NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典表';

-- ----------------------------
-- Table structure for t_dictionaryd
-- ----------------------------
CREATE TABLE `t_dictionaryd` (
  `id` varchar(20) character set utf8 collate utf8_general_ci default NULL,
  `pid` varchar(20) character set utf8 collate utf8_general_ci default NULL,
  `dictvalue` varchar(50) character set utf8 collate utf8_general_ci default NULL COMMENT 'label的名称根据父表的名称定',
  `state` smallint(6) default '1' COMMENT '启用=1,停用=0，默认启用'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_pattern
-- ----------------------------
CREATE TABLE `t_pattern` (
  `id` varchar(20) character set utf8 collate utf8_general_ci default NULL,
  `patterntype` varchar(20) character set utf8 collate utf8_general_ci default NULL,
  `patternname` varchar(100) character set utf8 collate utf8_general_ci default NULL,
  `remark` varchar(200) character set utf8 collate utf8_general_ci default NULL,
  `maker` varchar(20) character set utf8 collate utf8_general_ci default NULL,
  `makedatetime` datetime default NULL,
  `state` smallint(6) default '1' COMMENT '启用=1，停用=0，默认启用',
  `events` varchar(100) character set utf8 collate utf8_general_ci default NULL COMMENT '多选择，事件之间以半角逗号分隔',
  `tablename` varchar(20) character set utf8 collate utf8_general_ci default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_patternd
-- ----------------------------
CREATE TABLE `t_patternd` (
  `id` varchar(20) character set utf8 collate utf8_general_ci default NULL,
  `pid` varchar(20) character set utf8 collate utf8_general_ci default NULL,
  `fieldseqn` smallint(6) default NULL,
  `fielddesc` varchar(20) character set utf8 collate utf8_general_ci default NULL,
  `fieldname` varchar(20) character set utf8 collate utf8_general_ci default NULL,
  `fieldtype` varchar(10) character set utf8 collate utf8_general_ci default NULL COMMENT '字符、数字、日期、枚举、位置、照片  当选择字符时，字段长度只能整数并大于1，选择数字时，牧师字段类型为number型，长度允许有小数位，选择位置时，为字符型??长度固定30，经纬度按经续度之间以半角逗号分隔，如45.345233,118.342453。选择照片时，固定为字符串，长度200，用于保存照片的相对路径与文件。选择枚举时，选择数据字典中项目。',
  `fieldlength` varchar(10) character set utf8 collate utf8_general_ci default NULL,
  `remark` varchar(100) character set utf8 collate utf8_general_ci default NULL,
  `fieldenum` varchar(200) character set utf8 collate utf8_general_ci default NULL,
  `state` smallint(6) default NULL COMMENT '启用=1，停用=0，默认启用            此字段为备用字段，目前暂不使用'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='生成的物理表中必须有以下几个字段           编号、父表编号、执行人ID、状态(根据事件来定义，默认为0，对于正';

-- ----------------------------
-- Table structure for t_system_group
-- ----------------------------
CREATE TABLE `t_system_group` (
  `groupId` varchar(20) character set utf8 collate utf8_general_ci NOT NULL,
  `groupName` varchar(255) character set utf8 collate utf8_general_ci NOT NULL,
  `groupDesc` varchar(255) character set utf8 collate utf8_general_ci NOT NULL,
  `createId` varchar(20) character set utf8 collate utf8_general_ci NOT NULL,
  PRIMARY KEY  (`groupId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_system_grouprole
-- ----------------------------
CREATE TABLE `t_system_grouprole` (
  `GroupRoleId` varchar(20) character set utf8 collate utf8_general_ci NOT NULL,
  `GroupId` varchar(20) character set utf8 collate utf8_general_ci NOT NULL,
  `RoleId` varchar(20) character set utf8 collate utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_system_permission
-- ----------------------------
CREATE TABLE `t_system_permission` (
  `permissionId` varchar(20) character set utf8 collate utf8_general_ci NOT NULL,
  `permissionName` varchar(100) character set utf8 collate utf8_general_ci default NULL,
  `permissionResource` varchar(50) character set utf8 collate utf8_general_ci default NULL,
  `action` varchar(255) character set utf8 collate utf8_general_ci default NULL,
  `parentPermissionId` int(11) default NULL,
  `isMenu` smallint(1) default NULL,
  `permissionOrder` int(11) default NULL,
  `CreateId` int(11) default NULL,
  PRIMARY KEY  (`permissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_system_role
-- ----------------------------
CREATE TABLE `t_system_role` (
  `roleId` varchar(20) character set utf8 collate utf8_general_ci NOT NULL,
  `roleName` varchar(100) character set utf8 collate utf8_general_ci default NULL,
  `createId` varchar(20) character set utf8 collate utf8_general_ci default NULL,
  PRIMARY KEY  (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_system_rp
-- ----------------------------
CREATE TABLE `t_system_rp` (
  `RolePermissionId` varchar(20) character set utf8 collate utf8_general_ci NOT NULL,
  `RoleId` varchar(20) character set utf8 collate utf8_general_ci default NULL,
  `PermissionId` varchar(20) character set utf8 collate utf8_general_ci default NULL,
  PRIMARY KEY  (`RolePermissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_system_user
-- ----------------------------
CREATE TABLE `t_system_user` (
  `userId` int(11) NOT NULL auto_increment,
  `groupId` int(11) default NULL,
  `userName` varchar(50) character set utf8 collate utf8_general_ci NOT NULL,
  `passwd` varchar(40) character set utf8 collate utf8_general_ci default NULL,
  `repasswd` varchar(40) character set utf8 collate utf8_general_ci default NULL,
  PRIMARY KEY  (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_tomonitor
-- ----------------------------
CREATE TABLE `t_tomonitor` (
  `id` varchar(20) character set utf8 collate utf8_general_ci NOT NULL,
  `title` varchar(100) character set utf8 collate utf8_general_ci default NULL,
  `tomonitortype` varchar(20) character set utf8 collate utf8_general_ci default NULL COMMENT '字典表中增加 日常工作类别={由用户自定义}\r\n            本表中填入其值。',
  `missiongrade` varchar(10) character set utf8 collate utf8_general_ci default NULL COMMENT '字典表中增加  任务级别={日常任务、紧急任务、特殊任务}\r\n            在本表中，直接填入任务级别值。',
  `attachment` varchar(100) character set utf8 collate utf8_general_ci default NULL COMMENT '上传文件，此字段记录文件所在相对目录及文件名',
  `target` varchar(20) character set utf8 collate utf8_general_ci default NULL COMMENT '字典表中定义 工作指标={日、周、月}            本表中填入值',
  `targetvalue` smallint(6) default NULL COMMENT '手工录入，只能是整数',
  `feedbackpattern` varchar(20) character set utf8 collate utf8_general_ci default NULL COMMENT '关键反馈单的ID编号',
  `makedatetime` datetime default NULL,
  `maker` varchar(20) character set utf8 collate utf8_general_ci default NULL,
  `remark` varchar(200) character set utf8 collate utf8_general_ci default NULL,
  `executors` text character set utf8 collate utf8_general_ci COMMENT '选取各部门中的员工，员工之间以半角逗号分隔，最后一个员工不加逗号，如”张三,李四,王五“',
  `state` varchar(10) default NULL COMMENT '新任务、已审核、处理中、已中止、已完成、已归档            除了新任务外，其他状态不允许可删除任务单,已审核状态执行人手机就可收到任务，执行人读取后返回处理中，任务可以由创建人标记已中止，已完成，已归档',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='督办工作';

-- ----------------------------
-- Table structure for t_tomonitorexcute
-- ----------------------------
CREATE TABLE `t_tomonitorexcute` (
  `id` varchar(20) character set utf8 collate utf8_general_ci default NULL,
  `pid` varchar(20) character set utf8 collate utf8_general_ci default NULL,
  `executorid` varchar(20) character set utf8 collate utf8_general_ci default NULL,
  `status` smallint(6) default NULL COMMENT '处理中=0，已完成=1，已评份=9',
  `score` int(11) default NULL,
  `remark` varchar(200) character set utf8 collate utf8_general_ci default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='执行人列表';

-- ----------------------------
-- Table structure for td_sequences
-- ----------------------------
CREATE TABLE `td_sequences` (
  `seq_name` varchar(200) character set utf8 collate utf8_general_ci NOT NULL default '',
  `value` int(11) default '0',
  PRIMARY KEY  (`seq_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for u_0002
-- ----------------------------
CREATE TABLE `u_0002` (
  `id` int(11) NOT NULL auto_increment,
  `executorId` varchar(20) character set utf8 collate utf8_general_ci default NULL,
  `parentId` varchar(20) character set utf8 collate utf8_general_ci default NULL,
  `stateRemark` varchar(200) character set utf8 collate utf8_general_ci default NULL,
  `state` smallint(6) default '1',
  `zt` varchar(100) character set utf8 collate utf8_general_ci default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Procedure structure for pb_delAllSubPermission
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pb_delAllSubPermission`(in permissionId1 varchar(20)  CHARSET utf8)
BEGIN
call pb_getSubPermission(permissionId1,@a);
    if @a is not null then
    set @b=concat('\'',@a,'\'');
    end if;
 set @SqlCmd=concat("delete from t_system_permission where permissionId in(",@b,")");
PREPARE stmt FROM @SqlCmd;
EXECUTE stmt ;
END;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for pb_delRPById
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pb_delRPById`(in permissionId1 varchar(20) CHARSET utf8)
BEGIN
    call pb_getSubPermission(permissionId1,@a);
    if @a is not null then
    set @b=concat('\'',@a,'\'');
    end if;
 set @SqlCmd=concat("delete from t_system_rp where permissionId in(",@b,")");
PREPARE stmt FROM @SqlCmd;
EXECUTE stmt ;
END;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for pb_get_allPermission
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pb_get_allPermission`(userid1 varchar(20) CHARSET utf8)
BEGIN
declare done INT DEFAULT 0;
declare permissionId1 varchar(20);
DECLARE cur_permission CURSOR for
select f.permissionid from t_system_rp f join
(select a.roleid from t_system_grouprole a join (select groupid from t_system_user where userid=userid1) b 
on a.groupid=b.groupid) e on f.roleid=e.roleid;
DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;
CREATE  temporary   TABLE   if not exists  tmp_permission1 (permissionId varchar(20) character set utf8 collate utf8_general_ci,permissionName varchar(100) character set utf8 collate utf8_general_ci,parentPermissionId varchar(20) character set utf8 collate utf8_general_ci,permissionResource varchar(50) character set utf8 collate utf8_general_ci,action varchar(255) character set utf8 collate utf8_general_ci, permissionOrder int(13));
open cur_permission;
   repeat
	fetch  cur_permission into permissionId1;
	if not done then
          call pb_getsuperPermission(permissionId1);
        end  if;
  UNTIL done END REPEAT;
select distinct d.permissionId,d.permissionName,d.parentPermissionId,d.permissionResource,d.action,'0' as Checked from tmp_permission1 
	d order by permissionOrder;
drop table tmp_permission1;
END;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for pb_get_menu_tree
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pb_get_menu_tree`(userid1 varchar(20) CHARSET utf8)
begin
declare done INT DEFAULT 0;
declare permissionId1 varchar(20);
declare permissionOrder1 int;
declare permissionIds varchar(2000);
DECLARE cur_permission CURSOR for
select permissionid from (
select distinct permissionid from t_system_rp d join
(select a.roleid from t_system_grouprole a join (select groupid from t_system_user where userid=userid1) b
on a.groupid=b.groupid) c on d.roleid=c.roleid) t;
DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;
CREATE  temporary   TABLE   if not exists  tmp_permission (permissionId varchar(20) CHARSET utf8,permissionOrder int(13));
open cur_permission;
   repeat
	fetch  cur_permission into permissionId1;
	if not done then
          call proc_getAllSuperMenu(permissionId1);
        end  if;
  UNTIL done END REPEAT;
close cur_permission;
select * from ( select
    d.permissionId,d.permissionName,d.parentPermissionId,d.permissionResource,d.action,(
    SELECT COUNT(*) FROM (
        select permissionId from tmp_permission
    ) e WHERE e.permissionId = d.permissionId) as Checked from (
              (
                       select distinct f.permissionid from t_system_rp f join
                      (select a.roleid from t_system_grouprole a join (select groupid from t_system_user where userid=userid1) b
                       on a.groupid=b.groupid) e on f.roleid=e.roleid
               ) c Join t_system_permission d ON
    c.permissionId=d.permissionId and d.permissionId is not null) order by permissionOrder ) t
    where t.checked >0;
drop table tmp_permission;end;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for pb_getSubPermission
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pb_getSubPermission`(in permissionId1 varchar(20) CHARSET utf8,out result varchar(1000) CHARSET utf8)
BEGIN
declare done INT DEFAULT 0;
declare cur1 Cursor for  select permissionId  from t_system_permission where parentPermissionId=permissionId1;
DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;
SET @@max_sp_recursion_depth = 10; 
set result=permissionId1;
open cur1;
    repeat
	fetch  cur1 into permissionId1;
	if not done then
           call pb_getSubPermission(permissionId1, @a);
           set result=concat(result,'\',\'',@a);
           
	   
        end  if;
    UNTIL done END REPEAT;
    close cur1;
END;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for pb_getsuperPermission
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pb_getsuperPermission`(in permissionId1 int(13))
BEGIN
declare SJ_PermissionId int(13);
declare SJ_PermissionOrder int(13);
declare permissionOrder1 int(13);
declare ismenu1 int(13);
declare result_tmp varchar(8000) default '';
  select ismenu into ismenu1 from t_system_permission where permissionId=permissionId1;
  if(ismenu1='1') then
   select permissionOrder into permissionOrder1 from t_system_permission where ismenu=1 and permissionId=permissionId1;
  insert into tmp_permission1(permissionId,permissionName,parentPermissionId,permissionResource,action,permissionorder)
	select permissionId,permissionName,parentPermissionId,permissionResource,action,permissionorder from t_system_permission where 
        permissionId=permissionId1; 
  end if;
  lab1:while true do
      select parentPermissionId into SJ_PermissionId from t_system_permission where permissionId=permissionId1;
      
     
      if SJ_PermissionId='-1' or SJ_PermissionId is null  then
          leave LAB1;
      else
	 select permissionOrder into SJ_PermissionOrder from t_system_permission where ismenu=1 and permissionId=permissionId1;
           
	insert into tmp_permission1(permissionId,permissionName,parentPermissionId,permissionResource,action,permissionorder)
	select permissionId,permissionName,parentPermissionId,permissionResource,action,permissionorder from t_system_permission where 
        permissionId=SJ_PermissionOrder;
         set permissionId1=SJ_PermissionId;
      end if;
  end while lab1;
END;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for pro_delPatternById
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pro_delPatternById`(id1 varchar(20) CHARSET utf8,out info varchar(10) CHARSET utf8)
BEGIN
declare StrSql  Varchar(500);
set info=0;
start transaction;   
select tableName into @tableName from t_pattern where id=id1;
set StrSql=concat("drop table ",@tableName);
    set @a=StrSql;
    PREPARE stmt1 FROM @a;
        EXECUTE stmt1 ;
delete from t_pattern where id=id1;
delete from t_patternd where pid=id1;
 commit;
   set info='1';
END;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for proc_getAllsuperMenu
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_getAllsuperMenu`(in permissionId1 int(13))
BEGIN
declare SJ_PermissionId varchar(20);
declare SJ_PermissionOrder int(13);
declare permissionOrder1 int(13);
declare ismenu1 int(13);
declare result_tmp varchar(8000) default '';
  select ismenu into ismenu1 from t_system_permission where permissionId=permissionId1;
 
  if(ismenu1='1') then
   select permissionOrder into permissionOrder1 from t_system_permission where ismenu=1 and permissionId=permissionId1;
   insert into tmp_permission(permissionId,permissionOrder)values (permissionId1,permissionOrder1); 
  end if;
  lab1:while true do
      select parentPermissionId into SJ_PermissionId from t_system_permission where ismenu=1 and permissionId=permissionId1;
      
     
      if SJ_PermissionId='-1' or SJ_PermissionId is null  then
          leave LAB1;
      else
	 select permissionOrder into SJ_PermissionOrder from t_system_permission where ismenu=1 and permissionId=permissionId1;
         insert into tmp_permission(permissionId,permissionOrder)values (SJ_PermissionId,SJ_PermissionOrder);  
         
         set permissionId1=SJ_PermissionId;
      end if;
  end while lab1;
END;;
DELIMITER ;

-- ----------------------------
-- Function structure for FB_GetParm_ById
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `FB_GetParm_ById`(parmid varchar(30) CHARSET utf8,type_id varchar(30) CHARSET utf8) RETURNS char(100) CHARSET utf8
begin
/*
  ????????????????????????,type_id??????,id????,????????name
*/
declare v_value varchar(100);
set v_value='';
if parmid is null then
   return '';
end if;
if type_id='user' then
       select username  into v_value from t_system_user where userid=parmid  limit 1;
elseif type_id='pattern' then
      select PatternName into v_value  from T_Pattern where id=parmid limit 1;
elseif type_id='dictionary' then
      select dictvalue into  v_value from t_dictionaryd where id=parmid limit 1;
else
    set v_value='';
end if;
return trim(v_value);
end;;
DELIMITER ;

-- ----------------------------
-- Function structure for fun_getParentPermission
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `fun_getParentPermission`(permissionId1 int) RETURNS int(11)
BEGIN
declare parentPermissionId1 int;
declare result_tmp varchar(8000) default '';
 select distinct permissionid into @a from t_system_rp d join
                (select a.roleid from t_system_grouprole a join (select groupid from t_system_user where userid=1) b 
                on a.groupid=b.groupid) c on d.roleid=c.roleid;
  lab1:while true do
      select parentPermissionId into parentPermissionId1 from t_system_permission where permissionId=permissionId1;
     
      if parentPermissionId1='-1' or parentPermissionId1 is null  then
          leave LAB1;
      else
         set result_tmp=concat(parentPermissionId1,',',result_tmp);
         set permissionId1=parentPermissionId1;
      end if;
  end while lab1;
  return result_tmp;
END;;
DELIMITER ;

-- ----------------------------
-- Function structure for seqformat
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `seqformat`(in_seq_name char(200) CHARSET utf8) RETURNS char(100) CHARSET utf8
begin
	update td_sequences set value=value + 1 where seq_name =@in_seq_name;
	select ifnull(value,0) into @v_value from td_sequences where seq_name=@in_seq_name;
        select CURDATE()+0  into @v_date ;
        select lpad(concat(@v_value,''),8,'0') into @v_result;
        select concat(@v_date,@v_result) into @v_result;
        return @v_result;
end;;
DELIMITER ;

-- ----------------------------
-- Function structure for seqTableName
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `seqTableName`() RETURNS char(100) CHARSET utf8
BEGIN
update td_sequences set value=value + 1 where seq_name ='t_pattern_name';
	select ifnull(value,0) into @v_value from td_sequences where seq_name='t_pattern_name';
       
        select lpad(concat(@v_value,''),4,'0') into @v_result;
        return concat('u_',@v_result);
END;;
DELIMITER ;
-- ----------------------------
-- Function structure for FB_GetSeqn
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `FB_GetSeqn`(in_seq_name varchar(30) CHARSET utf8) RETURNS varchar(20) CHARSET utf8
begin
	select ifnull(value,0) into @v_value from td_sequences where seq_name=@in_seq_name;
        select CURDATE()+0  into @v_date ;
        select lpad(concat(@v_value,''),8,'0') into @v_result;
        select concat(@v_date,@v_result) into @v_result;
        return @v_result;
end;;
DELIMITER ;
-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `t_dailywork` VALUES ('1', '2010年冬季服装销售情况调查', '日常任务', '日常工作', '', '周', '100', '1', '2010-11-08 00:00:00', 'admini', '测试', null, '0');
INSERT INTO `t_dailywork` VALUES ('2', '2010年盼盼食品全国专营店巡检', '日常任务', '日常工作', '', '周', '150', '1', '2010-11-09 00:00:00', 'admini', '测试二', null, '0');
INSERT INTO `t_dailywork` VALUES ('2010120100000036', 'ddd', 'ff', '日常工作', '', '日', '1', 'df', '2010-12-01 00:00:00', 'dd', null, null, '0');
INSERT INTO `t_dailyworkexcute` VALUES ('1', '1', 'admini', '0', '0', '0');
INSERT INTO `t_dailyworkexcute` VALUES ('2', '1', 'admini', '0', '0', '0');
INSERT INTO `t_dailyworkexcute` VALUES ('3', '2', 'admini', '0', null, null);
INSERT INTO `t_dictionary` VALUES ('1', '采集类别', null, '0', '0');
INSERT INTO `t_dictionary` VALUES ('2', '服务态度', null, '0', '0');
INSERT INTO `t_dictionary` VALUES ('3', '商品名称', null, '0', '0');
INSERT INTO `t_dictionary` VALUES ('4', '事件', '', '1', '2001');
INSERT INTO `t_dictionary` VALUES ('5', '工作类别', '', '1', '1002');
INSERT INTO `t_dictionary` VALUES ('6', '任务级别', '', '1', '1001');
INSERT INTO `t_dictionary` VALUES ('7', '申报类别', '', '1', '1003');
INSERT INTO `t_dictionary` VALUES ('8', '督办类别', '', '1', '1004');
INSERT INTO `t_dictionaryd` VALUES ('1', '1', '质量', '1');
INSERT INTO `t_dictionaryd` VALUES ('2', '1', '数量', '1');
INSERT INTO `t_dictionaryd` VALUES ('3', '2', '非常好', '1');
INSERT INTO `t_dictionaryd` VALUES ('4', '2', '很好', '1');
INSERT INTO `t_dictionaryd` VALUES ('5', '2', '普通', '1');
INSERT INTO `t_dictionaryd` VALUES ('6', '2', '恶劣', '1');
INSERT INTO `t_dictionaryd` VALUES ('7', '3', '饼干', '1');
INSERT INTO `t_dictionaryd` VALUES ('8', '3', '矿泉水', '1');
INSERT INTO `t_dictionaryd` VALUES ('9', '3', '面包', '1');
INSERT INTO `t_dictionaryd` VALUES ('10', '6', '日常任务', '1');
INSERT INTO `t_dictionaryd` VALUES ('11', '6', '紧急任务', '1');
INSERT INTO `t_dictionaryd` VALUES ('12', '6', '特殊任务', '1');
INSERT INTO `t_dictionaryd` VALUES ('13', '5', '日常工作', '1');
INSERT INTO `t_dictionaryd` VALUES ('14', '5', '类别2', '1');
INSERT INTO `t_dictionaryd` VALUES ('15', '5', '类别3', '1');
INSERT INTO `t_dictionaryd` VALUES ('16', '7', '申报1', '1');
INSERT INTO `t_dictionaryd` VALUES ('17', '7', '申报2', '1');
INSERT INTO `t_dictionaryd` VALUES ('18', '8', '督办1', '1');
INSERT INTO `t_dictionaryd` VALUES ('19', '8', '督办2', '1');
INSERT INTO `t_dictionaryd` VALUES ('20', '4', '确认', '1');
INSERT INTO `t_dictionaryd` VALUES ('21', '4', '驳回', '1');
INSERT INTO `t_pattern` VALUES ('1', '1', '日常巡检', '															日常巡检工作\r\n					\r\n					\r\n					', '林圣如', '2010-12-09 16:11:05', '1', '11,12', 'u_0002');
INSERT INTO `t_patternd` VALUES (null, '1', '1', '主题', 'zt', 'varchar', '100', null, null, '1');
INSERT INTO `t_system_group` VALUES ('1', '系统管理员', '系统管理员', '1');
INSERT INTO `t_system_grouprole` VALUES ('1', '1', '1');
INSERT INTO `t_system_permission` VALUES ('0', '菜单', null, null, '-1', '1', '1', null);
INSERT INTO `t_system_permission` VALUES ('2000', '业务管理', null, null, '0', '1', '2000', '1');
INSERT INTO `t_system_permission` VALUES ('2001', '日常工作', 'work/tdailywork', null, '2000', '1', '2001', '1');
INSERT INTO `t_system_permission` VALUES ('2002', '督办工作', 'work/ttomonitor', null, '2000', '1', '2002', '1');
INSERT INTO `t_system_permission` VALUES ('2003', '事项申报', 'work/tdeclare', null, '2000', '1', '2003', '1');
INSERT INTO `t_system_permission` VALUES ('2004', '位置考勤', null, null, '2000', '1', '2004', '1');
INSERT INTO `t_system_permission` VALUES ('5000', '自定义管理', null, null, '0', '1', '5000', '1');
INSERT INTO `t_system_permission` VALUES ('5001', '自定义模板', '/custom', 'list&actionType=toQueryPage', '5000', '1', '5001', '1');
INSERT INTO `t_system_permission` VALUES ('9000', '系统管理', '', null, '0', '1', '9000', '1');
INSERT INTO `t_system_permission` VALUES ('9007', '角色组', 'group', '', '9000', '1', '9007', '1');
INSERT INTO `t_system_permission` VALUES ('9008', '角色', 'role', '', '9000', '1', '9008', '1');
INSERT INTO `t_system_permission` VALUES ('9009', '菜单管理', 'permission', 'list', '9000', '1', '9009', '1');
INSERT INTO `t_system_role` VALUES ('1', '超级用户', '1');
INSERT INTO `t_system_role` VALUES ('2010120800000029', '普通用户', '1');
INSERT INTO `t_system_rp` VALUES ('1', '1', '0');
INSERT INTO `t_system_rp` VALUES ('10', '1', '5001');
INSERT INTO `t_system_rp` VALUES ('11', '1', '9000');
INSERT INTO `t_system_rp` VALUES ('12', '1', '2000');
INSERT INTO `t_system_rp` VALUES ('13', '1', '5000');
INSERT INTO `t_system_rp` VALUES ('2', '1', '1000');
INSERT INTO `t_system_rp` VALUES ('3', '1', '9007');
INSERT INTO `t_system_rp` VALUES ('4', '1', '9008');
INSERT INTO `t_system_rp` VALUES ('5', '1', '9009');
INSERT INTO `t_system_rp` VALUES ('6', '1', '2001');
INSERT INTO `t_system_rp` VALUES ('7', '1', '2002');
INSERT INTO `t_system_rp` VALUES ('8', '1', '2003');
INSERT INTO `t_system_rp` VALUES ('9', '1', '2004');
INSERT INTO `t_system_user` VALUES ('1', '1', 'admini', 'admini', 'B1C21019AFD435194216C87F414E6CCE');
INSERT INTO `td_sequences` VALUES ('t_dailywork', '36');
INSERT INTO `td_sequences` VALUES ('t_declare', '5');
INSERT INTO `td_sequences` VALUES ('t_group', '27');
INSERT INTO `td_sequences` VALUES ('t_pattern', '1');
INSERT INTO `td_sequences` VALUES ('t_pattern_name', '2');
INSERT INTO `td_sequences` VALUES ('t_permission', '8');
INSERT INTO `td_sequences` VALUES ('t_role', '31');
INSERT INTO `td_sequences` VALUES ('t_toMonitor', '8');