/*
SQLyog - Free MySQL GUI v5.02
Host - 5.0.45-community-nt : Database - womobile
*********************************************************************
Server version : 5.0.45-community-nt
*/


create database if not exists `womobile`;

USE `womobile`;

/*Table structure for table `p_1` */

DROP TABLE IF EXISTS `p_1`;

CREATE TABLE `p_1` (
  `id` int(11) NOT NULL auto_increment,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `p_1` */

/*Table structure for table `p_2` */

DROP TABLE IF EXISTS `p_2`;

CREATE TABLE `p_2` (
  `id` int(11) NOT NULL auto_increment,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `p_2` */

/*Table structure for table `t_archives_menu` */

DROP TABLE IF EXISTS `t_archives_menu`;

CREATE TABLE `t_archives_menu` (
  `id` varchar(20) NOT NULL COMMENT '????',
  `name` varchar(50) default NULL COMMENT '????',
  `url` varchar(100) default NULL COMMENT '?????url',
  `img` varchar(100) default NULL COMMENT '??:??,?????????',
  `orderid` int(11) default NULL COMMENT '????:1????,2??????,3-4??',
  `powvalue` int(11) default NULL COMMENT '9:?????,1:?????\r\n            x<9:???',
  `layerid` char(2) default NULL COMMENT '1:???,2:???'
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='????html????????';

/*Data for the table `t_archives_menu` */

insert into `t_archives_menu` values 
('1','????','','',1000,9,'1'),
('2','???','/custom.do','user1_mobilephone.png',1001,9,'2'),
('3','??','','',2000,9,'1'),
('4','????','/work/tdailywork.do','user1_mobilephone.png',3000,9,'2'),
('5','????','/work/ttomonitor.do','user1_mobilephone.png',3000,9,'2'),
('6','????','/work/tdeclare.do','user1_mobilephone.png',3000,9,'2');

/*Table structure for table `t_archives_user` */

DROP TABLE IF EXISTS `t_archives_user`;

CREATE TABLE `t_archives_user` (
  `id` varchar(20) NOT NULL COMMENT '??',
  `code` varchar(40) default NULL COMMENT '???',
  `name` varchar(20) default NULL COMMENT '??',
  `passwd` char(32) default NULL COMMENT '??\r\n            ?????????????????????',
  `bill_type` char(2) default NULL COMMENT '0:??\r\n            1:??',
  `bill_state` char(2) default NULL COMMENT '0???\r\n            1???',
  `create_date` datetime default NULL COMMENT '????',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='????';

/*Data for the table `t_archives_user` */

insert into `t_archives_user` values 
('1','admini','admini','admini','1','1','2010-11-04 00:00:00');

/*Table structure for table `t_dailywork` */

DROP TABLE IF EXISTS `t_dailywork`;

CREATE TABLE `t_dailywork` (
  `id` varchar(20) NOT NULL COMMENT '??',
  `title` varchar(100) default NULL COMMENT '??',
  `dailyworktype` varchar(20) default NULL COMMENT '??????',
  `missiongrade` varchar(10) default NULL COMMENT '????',
  `attachment` varchar(100) default NULL COMMENT '??',
  `target` varchar(20) default NULL COMMENT '??',
  `targetvalue` smallint(6) default NULL COMMENT '???',
  `feedbackpattern` varchar(20) default NULL COMMENT '?????',
  `makedatetime` datetime default NULL COMMENT '????',
  `maker` varchar(20) default NULL COMMENT '???',
  `remark` varchar(200) default NULL COMMENT '? ?',
  `executors` text COMMENT '???',
  `state` varchar(10) default NULL COMMENT '????',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='?????';

/*Data for the table `t_dailywork` */

insert into `t_dailywork` values 
('2010111300000026','1','1',NULL,NULL,NULL,NULL,NULL,'2010-11-13 22:24:43',NULL,NULL,NULL,'0'),
('2010111400000027','test','test',NULL,NULL,NULL,NULL,NULL,'2010-11-14 17:31:15',NULL,NULL,NULL,'???'),
('2010111400000028','e','e','e',NULL,NULL,NULL,NULL,'2010-11-14 17:52:50',NULL,NULL,NULL,'???'),
('2010111400000029','b','b','b','b','b',NULL,NULL,'2010-11-14 17:53:40',NULL,NULL,NULL,'???'),
('2010111400000030','tt','tt',NULL,NULL,NULL,NULL,NULL,'2010-11-14 18:21:00',NULL,NULL,NULL,'???'),
('2010111400000031','xx','xx',NULL,NULL,NULL,NULL,NULL,'2010-11-14 18:21:11',NULL,NULL,NULL,'???'),
('2010111400000032','132','xx',NULL,NULL,NULL,NULL,NULL,'2010-11-14 18:21:31',NULL,NULL,NULL,'???'),
('2010111400000033','qe',NULL,NULL,NULL,NULL,NULL,NULL,'2010-11-14 18:23:18',NULL,NULL,NULL,'???'),
('2010111400000034','45','345',NULL,NULL,NULL,NULL,NULL,'2010-11-14 18:27:52',NULL,NULL,NULL,'???'),
('2010111700000035','123','123','????','123','?',123,'123','2010-11-17 16:17:08','123',NULL,'123','???');

/*Table structure for table `t_dailyworkexcute` */

DROP TABLE IF EXISTS `t_dailyworkexcute`;

CREATE TABLE `t_dailyworkexcute` (
  `id` varchar(20) default NULL,
  `pid` varchar(20) default NULL,
  `executorid` varchar(20) default NULL,
  `status` smallint(6) default NULL COMMENT '???=0????=1????=9',
  `score` int(11) default NULL,
  `remark` varchar(200) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='?????';

/*Data for the table `t_dailyworkexcute` */

/*Table structure for table `t_declare` */

DROP TABLE IF EXISTS `t_declare`;

CREATE TABLE `t_declare` (
  `id` varchar(20) NOT NULL COMMENT '??',
  `title` varchar(100) default NULL COMMENT '??',
  `declaretype` varchar(20) default NULL COMMENT '????',
  `missiongrade` varchar(10) default NULL COMMENT '????',
  `attachment` varchar(100) default NULL COMMENT '??',
  `target` varchar(20) default NULL COMMENT '??',
  `targetvalue` smallint(6) default NULL COMMENT '???',
  `declarepattern` varchar(20) default NULL COMMENT '??????',
  `makedatetime` datetime default NULL COMMENT '????',
  `maker` varchar(20) default NULL COMMENT '???',
  `remark` varchar(200) default NULL COMMENT '??',
  `executors` text COMMENT '???',
  `state` varchar(10) default NULL COMMENT '????',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='????';

/*Data for the table `t_declare` */

insert into `t_declare` values 
('2010111300000001','3','3',NULL,NULL,NULL,NULL,NULL,'2010-11-13 22:25:25',NULL,NULL,NULL,'???'),
('2010111400000002','wr','wer',NULL,NULL,NULL,NULL,NULL,'2010-11-14 18:28:54',NULL,NULL,NULL,'???'),
('2010111400000003','bb','bb',NULL,NULL,NULL,NULL,NULL,'2010-11-14 18:29:00',NULL,NULL,NULL,'???'),
('2010111600000004','54','456',NULL,NULL,NULL,NULL,NULL,'2010-11-16 11:54:20',NULL,NULL,NULL,'0'),
('2010111700000005','123','123','????','123','?',123,'132','2010-11-17 16:17:37','123','12','123','???');

/*Table structure for table `t_declareexcute` */

DROP TABLE IF EXISTS `t_declareexcute`;

CREATE TABLE `t_declareexcute` (
  `id` varchar(20) default NULL,
  `pid` varchar(20) default NULL,
  `executorid` varchar(20) default NULL,
  `status` smallint(6) default NULL COMMENT '???=0????=1????=9',
  `score` int(11) default NULL,
  `remark` varchar(200) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='?????';

/*Data for the table `t_declareexcute` */

/*Table structure for table `t_dictionary` */

DROP TABLE IF EXISTS `t_dictionary`;

CREATE TABLE `t_dictionary` (
  `??` varchar(20) NOT NULL,
  `??` varchar(50) default NULL,
  `??` varchar(100) default NULL,
  PRIMARY KEY  (`??`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='?????';

/*Data for the table `t_dictionary` */

/*Table structure for table `t_dictionaryd` */

DROP TABLE IF EXISTS `t_dictionaryd`;

CREATE TABLE `t_dictionaryd` (
  `id` varchar(20) default NULL,
  `pid` varchar(20) default NULL,
  `dictvalue` varchar(50) default NULL COMMENT 'label???????????',
  `state` smallint(6) default '1' COMMENT '??=1,??=0?????'
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `t_dictionaryd` */

/*Table structure for table `t_pattern` */

DROP TABLE IF EXISTS `t_pattern`;

CREATE TABLE `t_pattern` (
  `id` varchar(20) default NULL,
  `patterntype` varchar(20) default NULL,
  `patternname` varchar(100) default NULL,
  `remark` varchar(200) default NULL,
  `maker` varchar(20) default NULL,
  `makedatetime` datetime default NULL,
  `state` smallint(6) default '1' COMMENT '??=1???=0?????',
  `events` varchar(100) default NULL COMMENT '???????????????',
  `tablename` varchar(20) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `t_pattern` */

insert into `t_pattern` values 
('1','1','','',NULL,'2010-11-18 23:00:30',0,'',''),
('1','1','123','123',NULL,'2010-11-19 09:14:27',1,'123','123'),
('1','1','123','123',NULL,'2010-11-19 09:16:23',1,'123','123'),
('1','1','1','1',NULL,'2010-11-19 09:17:42',1,'1','1'),
('1','1','2','2',NULL,'2010-11-19 09:19:14',1,'2','2');

/*Table structure for table `t_patternd` */

DROP TABLE IF EXISTS `t_patternd`;

CREATE TABLE `t_patternd` (
  `id` varchar(20) default NULL,
  `pid` varchar(20) default NULL,
  `fieldseqn` smallint(6) default NULL,
  `fielddesc` varchar(20) default NULL,
  `fieldname` varchar(20) default NULL,
  `fieldtype` varchar(10) default NULL COMMENT '?????????????????\r\n            ??????????????????1??????????????number??????????????????????????30???????????????????45.345233,118.342453????????????????200????????????????????????????????',
  `fieldlength` varchar(10) default NULL,
  `remark` varchar(100) default NULL,
  `fieldenum` varchar(200) default NULL,
  `state` smallint(6) default NULL COMMENT '??=1???=0?????\r\n            ???????????????'
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='????????????????\r\n           ???????????ID???(???????????0??';

/*Data for the table `t_patternd` */

insert into `t_patternd` values 
(NULL,'1',123,'123','123','int','123','',NULL,1),
(NULL,'1',123,'132','123','varchar','132','123',NULL,1),
(NULL,'1',1,'1','1','varchar','1','1',NULL,1);

/*Table structure for table `t_tomonitor` */

DROP TABLE IF EXISTS `t_tomonitor`;

CREATE TABLE `t_tomonitor` (
  `id` varchar(20) NOT NULL COMMENT '??',
  `title` varchar(100) default NULL COMMENT '??',
  `tomonitortype` varchar(20) default NULL COMMENT '????',
  `missiongrade` varchar(10) default NULL COMMENT '????',
  `attachment` varchar(100) default NULL COMMENT '??',
  `target` varchar(20) default NULL COMMENT '??',
  `targetvalue` smallint(6) default NULL COMMENT '???',
  `feedbackpattern` varchar(20) default NULL COMMENT '?????',
  `makedatetime` datetime default NULL COMMENT '????',
  `maker` varchar(20) default NULL COMMENT '???',
  `remark` varchar(200) default NULL COMMENT '??',
  `executors` text COMMENT '???',
  `state` varchar(10) default NULL COMMENT '????',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='????';

/*Data for the table `t_tomonitor` */

insert into `t_tomonitor` values 
('2010111300000001','2','2',NULL,NULL,NULL,NULL,NULL,'2010-11-13 22:25:16',NULL,NULL,NULL,'???'),
('2010111400000002','xv','sdf',NULL,NULL,NULL,NULL,NULL,'2010-11-14 17:54:39',NULL,NULL,NULL,'0'),
('2010111400000003','3','3',NULL,NULL,NULL,NULL,NULL,'2010-11-14 18:23:04',NULL,NULL,NULL,'???'),
('2010111400000004','xx','xx',NULL,NULL,NULL,NULL,NULL,'2010-11-14 18:28:04',NULL,NULL,NULL,'???'),
('2010111400000005','ht','et',NULL,NULL,NULL,NULL,NULL,'2010-11-14 18:28:13',NULL,NULL,NULL,'???'),
('2010111600000006','123456799asdagseqweqweqwe','e','23',NULL,NULL,NULL,NULL,'2010-11-16 11:06:12',NULL,NULL,NULL,'???'),
('2010111700000007','123','123','????',NULL,NULL,NULL,NULL,'2010-11-17 16:07:57','123',NULL,NULL,'???'),
('2010111700000008','123','123','????','123','?',132,'123','2010-11-17 16:17:23','123',NULL,'123','???');

/*Table structure for table `t_tomonitorexcute` */

DROP TABLE IF EXISTS `t_tomonitorexcute`;

CREATE TABLE `t_tomonitorexcute` (
  `id` varchar(20) default NULL,
  `pid` varchar(20) default NULL,
  `executorid` varchar(20) default NULL,
  `status` smallint(6) default NULL COMMENT '???=0????=1????=9',
  `score` int(11) default NULL,
  `remark` varchar(200) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='?????';

/*Data for the table `t_tomonitorexcute` */

/*Table structure for table `td_sequences` */

DROP TABLE IF EXISTS `td_sequences`;

CREATE TABLE `td_sequences` (
  `seq_name` varchar(200) NOT NULL default '' COMMENT '???????????????',
  `value` int(11) default '0',
  PRIMARY KEY  (`seq_name`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='Sequences?';

/*Data for the table `td_sequences` */

insert into `td_sequences` values 
('t_dailywork',35),
('t_declare',5),
('t_pattern',0),
('t_toMonitor',8);

/* Function  structure for function  `seqformat` */

drop function  if exists `seqformat`;

DELIMITER $$;

CREATE DEFINER=`root`@`localhost` FUNCTION `seqformat`(in_seq_name char(200)) RETURNS char(100) CHARSET gbk
BEGIN
	update td_sequences set value=value + 1 where seq_name =@in_seq_name;
	select ifnull(value,0) into @v_value from td_sequences where seq_name=@in_seq_name;
        select CURDATE()+0  into @v_date ;
        select lpad(concat(@v_value,''),8,'0') into @v_result;
        select concat(@v_date,@v_result) into @v_result;
        return @v_result;
END$$

DELIMITER ;$$




ELIMITER $$;

DROP FUNCTION IF EXISTS `womobile`.`FB_GetParm_ById`$$

CREATE DEFINER=`root`@`localhost` FUNCTION `FB_GetParm_ById`(parmid varchar(30),type_id varchar(30)) RETURNS char(100) CHARSET gbk
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
       select name  into v_value from t_archives_user where id=parmid  limit 1;
elseif type_id='pattern' then
      select PatternName into v_value  from T_Pattern where id=parmid limit 1;
elseif type_id='dictionary' then
      select dictvalue into  v_value from t_dictionaryd where id=parmid limit 1;
else
    set v_value='';
end if;
return trim(v_value);
end$$

DELIMITER ;$$


INSERT INTO `t_dictionaryd` VALUES ('1', '1001', '日常任务', '1');
INSERT INTO `t_dictionaryd` VALUES ('2', '1001', '紧急任务', '1');
INSERT INTO `t_dictionaryd` VALUES ('3', '1001', '特殊任务', '1');
INSERT INTO `t_dictionaryd` VALUES ('4', '1002', '类别1', '1');
INSERT INTO `t_dictionaryd` VALUES ('5', '1002', '类别2', '1');
INSERT INTO `t_dictionaryd` VALUES ('6', '1002', '类别3', '1');
INSERT INTO `t_dictionaryd` VALUES ('7', '1003', '申报1', '1');
INSERT INTO `t_dictionaryd` VALUES ('8', '1003', '申报2', '1');
INSERT INTO `t_dictionaryd` VALUES ('9', '1004', '督办1', '1');
INSERT INTO `t_dictionaryd` VALUES ('10', '1004', '督办2', '1');

/*20110118修改*/
/*用户机构树*/
/*三个存储过程pb_getBranchUserTree  pb_getSubBranchUserTree   pb_getUserTree*/

DELIMITER $$;

DROP PROCEDURE IF EXISTS `womobile`.`pb_getBranchUserTree`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `pb_getBranchUserTree`(in branchId1 varchar(20) CHARSET utf8)
BEGIN
declare done INT DEFAULT 0;
declare branchName1 varchar(100);
declare parentBranchId1 varchar(20);
declare branchOrder1 int;
declare checked1 char(1);
DECLARE cur_branch CURSOR for
select branchid from t_system_branch where parentBranchId=branchId1 order by branchOrder;
DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;
SET @@max_sp_recursion_depth = 10;
CREATE  temporary   TABLE   if not exists  tmp_branchTree (branchId varchar(20) character set utf8 collate utf8_general_ci,branchName varchar(100) character set utf8 collate utf8_general_ci,parentBranchId varchar(20) character set utf8 collate utf8_general_ci, branchOrder int(13),checked char(1));

select branchName into branchName1 from t_system_branch where branchId=branchId1;

select parentBranchId  into parentBranchId1 from t_system_branch where branchId=branchId1;

select branchOrder into branchOrder1 from t_system_branch where branchId=branchId1;

insert into tmp_branchTree(branchId,branchName,parentBranchId,branchOrder,checked) values(branchId1,branchName1,parentBranchId1,branchOrder1,1);

open cur_branch;
   repeat
	fetch  cur_branch into branchId1;
	if not done then
          call pb_getSubBranchUserTree(branchId1);
      else
        call pb_getUserTree(branchId1);
        end  if;
  UNTIL done END REPEAT;
  select * from tmp_branchTree ;
  drop table tmp_branchTree;
END$$

DELIMITER ;$$


DELIMITER $$;

DROP PROCEDURE IF EXISTS `womobile`.`pb_getSubBranchUserTree`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `pb_getSubBranchUserTree`(in branchId1 varchar(20) CHARSET utf8)
BEGIN
declare done INT DEFAULT 0;
declare branchName1 varchar(100);
declare parentBranchId1 varchar(20);
declare branchOrder1 int;
declare checked1 char(1);
DECLARE cur_branch CURSOR for
select branchid from t_system_branch where parentBranchId=branchId1 order by branchOrder;
DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;
SET @@max_sp_recursion_depth = 10;
select branchName into branchName1 from t_system_branch where branchId=branchId1;


select parentBranchId  into parentBranchId1 from t_system_branch where branchId=branchId1;
select branchOrder into branchOrder1 from t_system_branch where branchId=branchId1;
insert into tmp_branchTree(branchId,branchName,parentBranchId,branchOrder,checked) values(branchId1,branchName1,parentBranchId1,branchOrder1,1);
open cur_branch;
   repeat
	fetch  cur_branch into branchId1;
	if not done then
          call pb_getSubBranchUserTree(branchId1);
       else
        call pb_getUserTree(branchId1);
        end  if;
  UNTIL done END REPEAT;
END$$

DELIMITER ;$$

DELIMITER $$;

DROP PROCEDURE IF EXISTS `womobile`.`pb_getUserTree`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `pb_getUserTree`(in branchId1 varchar(20) CHARSET utf8)
BEGIN
 declare concount int(11);      /*需任务的任务数*/

  select ifnull(count(*),0) into concount from t_system_user where branchId=branchId1;

  if concount!=0 then
insert into tmp_branchTree(branchId,branchName,parentBranchId,branchOrder,checked)
select  id,username,branchId1 ,1,0 from t_system_user where branchId=branchId1;
 end if;

END$$

DELIMITER ;$$


/*一个函数用户信息*/
DELIMITER $$;

DROP FUNCTION IF EXISTS `womobile`.`FB_GetParm_ById`$$

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
elseif type_id='userex' then
     select username  into  v_value from t_system_user  where id=parmid limit 1;
else
    set v_value='';
end if;
return trim(v_value);
end$$

DELIMITER ;$$

/*更新主键代码*/
td_sequences中增加
t_reportpattern
t_reportpatternc
t_reportpatternf

/*2010-01-20*/
/*
说明
修改原存储过程(3个):pb_getSubBranchUserTree,pb_getUserTree,pb_getBranchUserTree
增加存储过程(1个):Proc_getAllSuperBranch
增加函数(1个):Func_getAllSuperBranch
表t_reportpattern增加一个字段patterntype
*/
DELIMITER $$;

DROP PROCEDURE IF EXISTS `womobile`.`pb_getUserTree`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `pb_getUserTree`(in branchId1 varchar(20) CHARSET utf8)
BEGIN
 declare concount int(11);      /*需任务的任务数*/

  select ifnull(count(*),0) into concount from t_system_user where branchId=branchId1;

  if concount!=0 then
insert into tmp_branchTree(branchId,branchName,parentBranchId,branchOrder,checked)
select  concat(id,'_ex'),username,branchId1 ,1,0 from t_system_user where branchId=branchId1;
 end if;

END$$

DELIMITER ;$$


DELIMITER $$;

DROP PROCEDURE IF EXISTS `womobile`.`pb_getSubBranchUserTree`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `pb_getSubBranchUserTree`(in branchId1 varchar(20) CHARSET utf8)
BEGIN
declare done INT DEFAULT 0;
declare branchName1 varchar(100);
declare parentBranchId1 varchar(20);
declare branchOrder1 int;
declare checked1 char(1);
declare flag int;

DECLARE cur_branch CURSOR for
select branchid from t_system_branch where parentBranchId=branchId1 order by branchOrder;
DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;
SET @@max_sp_recursion_depth = 10;
select branchName into branchName1 from t_system_branch where branchId=branchId1;


select parentBranchId  into parentBranchId1 from t_system_branch where branchId=branchId1;
select branchOrder into branchOrder1 from t_system_branch where branchId=branchId1;
insert into tmp_branchTree(branchId,branchName,parentBranchId,branchOrder,checked) values(branchId1,branchName1,parentBranchId1,branchOrder1,1);

set flag=1;

open cur_branch;
   repeat
	fetch  cur_branch into branchId1;
	if not done then
         set flag=0;
          call pb_getSubBranchUserTree(branchId1);
        end  if;
  UNTIL done END REPEAT;

if flag=1 then
        call pb_getUserTree(branchId1);
end if;

END$$

DELIMITER ;$$


DELIMITER $$;

DROP PROCEDURE IF EXISTS `womobile`.`pb_getBranchUserTree`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `pb_getBranchUserTree`(in branchId1 varchar(20) CHARSET utf8)
BEGIN
declare done INT DEFAULT 0;
declare branchName1 varchar(100);
declare parentBranchId1 varchar(20);
declare branchOrder1 int;
declare checked1 char(1);
DECLARE cur_branch CURSOR for
select branchid from t_system_branch where parentBranchId=branchId1 order by branchOrder;
DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;
SET @@max_sp_recursion_depth = 10;
CREATE  temporary   TABLE   if not exists  tmp_branchTree (branchId varchar(20) character set utf8 collate utf8_general_ci,branchName varchar(100) character set utf8 collate utf8_general_ci,parentBranchId varchar(20) character set utf8 collate utf8_general_ci, branchOrder int(13),checked char(1));

select branchName into branchName1 from t_system_branch where branchId=branchId1;

select parentBranchId  into parentBranchId1 from t_system_branch where branchId=branchId1;

select branchOrder into branchOrder1 from t_system_branch where branchId=branchId1;

insert into tmp_branchTree(branchId,branchName,parentBranchId,branchOrder,checked) values(branchId1,branchName1,parentBranchId1,branchOrder1,1);

open cur_branch;
   repeat
	fetch  cur_branch into branchId1;
	if not done then
          call pb_getSubBranchUserTree(branchId1);
        end  if;
  UNTIL done END REPEAT;
  select * from tmp_branchTree ;
  drop table tmp_branchTree;
END$$

DELIMITER ;$$


DELIMITER $$;

DROP PROCEDURE IF EXISTS `womobile`.`Proc_getAllSuperBranch`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_getAllSuperBranch`(idkey varchar(20),out result varchar(8000))
BEGIN
/*查询上级机构信息,递归查询到根结点(不包括本结点)*/
declare parentidkey varchar(20);/*临时根结点*/
declare result_tmp varchar(8000) default '';/*临时结果集*/
set result_tmp=idkey ;
  lab1:while true do/*定义标签,为退出循环服务*/
     /*查询上级父结点*/
      select parentBranchId into parentidkey from t_system_branch where branchId=idkey;
      /*当月到达根时退出*/
      if parentidkey='-1' or parentidkey is null  then
          leave LAB1;
      else
      /*连接每个根数据*/
         set result_tmp=concat(parentidkey,',',result_tmp);
     /*设置根的设置*/
         set idkey=parentidkey;
      end if;
  end while lab1;
  set result=result_tmp;
END$$

DELIMITER ;$$


DELIMITER $$;

DROP FUNCTION IF EXISTS `womobile`.`Func_getAllSuperBranch`$$

CREATE DEFINER=`root`@`localhost` FUNCTION `Func_getAllSuperBranch`(branchId1 varchar(20)) RETURNS varchar(8000) CHARSET utf8
BEGIN
call Proc_getAllSuperBranch(branchId1,@tmp);
return  @tmp;
END$$

DELIMITER ;$$


alter table t_reportpattern  add patterntype varchar(20) default ''   

/*2011-01-27:增加主表固定字段说明*/

表t_patternd中字段
主表类型
ParentType
日常工作=2001,督办工作=2002,事项申报=2003
修改为:
ParentType
日常工作=1,督办工作=2,事项申报=3