drop table if exists eop_app;

drop table if exists eop_installer;

drop table if exists eop_layout;

drop table if exists eop_product;

drop table if exists eop_site;

drop table if exists eop_siteadmin;

drop table if exists eop_siteapp;

drop table if exists eop_sitedomain;

drop table if exists eop_user;

drop table if exists eop_useradmin;

drop table if exists eop_userdetail;

drop table if exists es_admintheme;

drop table if exists es_border;

drop table if exists es_menu;

drop table if exists es_theme;

drop table if exists es_themeuri;

drop table if exists es_widgetbundle;


drop table if exists es_access;

drop table if exists eop_error_report;

/*==============================================================*/
/* Table: eop_error_report                                      */
/*==============================================================*/
create table eop_error_report
(
   id                   int not null auto_increment,
   error                text,
   info                 text,
   dateline             int,
   primary key (id)
);


/*==============================================================*/
/* Table: es_access                                             */
/*==============================================================*/
create table es_access
(
   id                   mediumint not null auto_increment,
   ip                   varchar(255),
   url                  varchar(1000),
   page                 varchar(255),
   area                 varchar(255),
   access_time          int,
   stay_time            int,
   point                int,
   primary key (id)
);


/*==============================================================*/
/* Index: Index_ip                                              */
/*==============================================================*/
create index Index_ip on es_access
(
   ip
);

/*==============================================================*/
/* Index: Index_url                                             */
/*==============================================================*/
create index Index_url on es_access
(
   url
);


/*==============================================================*/
/* Table: eop_app                                               */
/*==============================================================*/
create table eop_app
(
   id                   int not null auto_increment,
   appid                varchar(50),
   app_name             varchar(50),
   author               varchar(50),
   descript             Text,
   deployment           int default 1 comment '0:本地；1：远程',
   path                 varchar(255) comment '对本地是目录，对远程是地址',
   authorizationcode    varchar(50),
   installuri           varchar(255),
   deleteflag           smallint default 0,
   primary key (id)
);

/*==============================================================*/
/* Table: eop_installer                                         */
/*==============================================================*/
create table eop_installer
(
   id                   bigint(8) not null auto_increment,
   ip                   varchar(50),
   version              varchar(50),
   remark               text,
   installtime          bigint(24),
   primary key (id)
);

/*==============================================================*/
/* Table: eop_layout                                            */
/*==============================================================*/
create table eop_layout
(
   id                   int not null auto_increment,
   appid                varchar(50),
   layoutname           varchar(50),
   path                 varchar(255) comment '对本地是目录，对远程是地址',
   primary key (id)
);

/*==============================================================*/
/* Table: eop_product                                           */
/*==============================================================*/
create table eop_product
(
   id                   int not null auto_increment,
   productid            varchar(50),
   product_name         varchar(50),
   author               varchar(50),
   descript             Text,
   createtime           bigint,
   version              varchar(255),
   preview              varchar(255),
   primary key (id)
);

/*==============================================================*/
/* Table: eop_site                                              */
/*==============================================================*/
create table eop_site
(
   id                   int not null auto_increment,
   userid               int,
   sitename             varchar(255),
   productid            varchar(50),
   descript             text,
   icofile              varchar(255),
   logofile             varchar(255),
   deleteflag           smallint default 0 comment '0:正常;1:已删除',
   keywords             varchar(255),
   themepath            varchar(50),
   adminthemeid         int,
   themeid              int,
   point                int default 0,
   primary key (id)
);


/*==============================================================*/
/* Table: eop_siteadmin                                         */
/*==============================================================*/
create table eop_siteadmin
(
   id                   int not null auto_increment,
   managerid            int,
   siteid               int,
   userid               int,
   primary key (id)
);

/*==============================================================*/
/* Table: eop_siteapp                                           */
/*==============================================================*/
create table eop_siteapp
(
   id                   int not null auto_increment,
   siteid               int,
   app_id               int,
   userid               int,
   appid                varchar(50),
   deleteflag           smallint default 0,
   primary key (id)
);

/*==============================================================*/
/* Table: eop_sitedomain                                        */
/*==============================================================*/
create table eop_sitedomain
(
   id                   int not null auto_increment,
   domain               varchar(255),
   domaintype           smallint default 0 comment '0:二级域名;1:独立域名',
   siteid               int,
   userid               int,
   status               smallint default 0 comment '0:已开启;1:已关闭',
   primary key (id)
);

/*==============================================================*/
/* Table: eop_user                                              */
/*==============================================================*/
create table eop_user
(
   id                   int not null auto_increment,
   username             varchar(50),
   address              varchar(255),
   legalrepresentative  varchar(50),
   linkman              varchar(50),
   tel                  varchar(50),
   mobile               varchar(50),
   email                varchar(50),
   logofile             varchar(255),
   licensefile          varchar(255),
   defaultsiteid        int,
   deleteflag           smallint default 0 comment '0：正常；1：已删除;2:未审核,默认是未审核',
   usertype             smallint comment '0个人
            1公司',
   primary key (id)
);

/*==============================================================*/
/* Table: eop_useradmin                                         */
/*==============================================================*/
create table eop_useradmin
(
   id                   int not null auto_increment,
   userid               int,
   username             varchar(50),
   realname             varchar(50),
   password             varchar(50),
   tel                  varchar(50),
   mobile               varchar(50),
   email                varchar(50),
   qq                   varchar(50),
   defaultsiteid        int,
   primary key (id)
);

/*==============================================================*/
/* Table: eop_userdetail                                        */
/*==============================================================*/
create table eop_userdetail
(
   id                   int not null auto_increment,
   userid               int,
   bussinessscope       text,
   regaddress           varchar(255),
   regdate              bigint(8),
   corpscope            smallint default 0 comment '0:未知;1:1-10人;2:11-50人;3:51-100人;4:101-500人;5:501-1000人;6:1000人以上',
   corpdescript         text,
   primary key (id)
);

/*==============================================================*/
/* Table: es_admintheme                                         */
/*==============================================================*/
create table es_admintheme
(
   id                   int not null auto_increment,
   appid                varchar(50),
   siteid               int,
   themename            varchar(50),
   path                 varchar(255) comment '对本地是目录，对远程是地址',
   userid               int,
   author               varchar(50),
   version              varchar(50),
   framemode            smallint default 0 comment '/**
            	 * 0否
            	 * 1是
            	 */',
   deleteflag           smallint default 0 comment '0:正常;1:已删除',
   thumb                varchar(50),
   primary key (id)
);

/*==============================================================*/
/* Table: es_border                                             */
/*==============================================================*/
create table es_border
(
   id                   int not null auto_increment,
   borderid             varchar(50),
   bordername           varchar(50),
   themepath            varchar(50),
   deleteflag           smallint default 0 comment '0:正常;1:已删除',
   primary key (id)
);

/*==============================================================*/
/* Table: es_menu                                               */
/*==============================================================*/
create table es_menu
(
   id                   int not null auto_increment,
   appid                varchar(50),
   pid                  int,
   title                varchar(50),
   url                  varchar(255),
   target               varchar(255),
   sorder               int default 50,
   menutype             integer,
   datatype             varchar(50),
   selected             smallint default 0 comment '0:False;1:True default 0',
   deleteflag           smallint default 0 comment '0:正常;1:已删除',
   primary key (id)
);

/*==============================================================*/
/* Table: es_theme                                              */
/*==============================================================*/
create table es_theme
(
   id                   int not null auto_increment,
   appid                varchar(50),
   themename            varchar(50),
   path                 varchar(255) comment '对本地是目录，对远程是地址',
   author               varchar(50),
   version              varchar(50),
   deleteflag           smallint default 0 comment '0:正常;1:已删除',
   thumb                varchar(50),
   primary key (id)
);

/*==============================================================*/
/* Table: es_themeuri                                           */
/*==============================================================*/
create table es_themeuri
(
   id                   int not null auto_increment,
   themeid              int,
   uri                  varchar(255),
   path                 varchar(255),
   deleteflag           smallint default 0 comment '0:正常;1:已删除',
   pagename             varchar(255),
   point                int,
   primary key (id)
);


/*==============================================================*/
/* Table: es_widgetbundle                                       */
/*==============================================================*/
create table es_widgetbundle
(
   id                   int not null auto_increment,
   widgetname           varchar(50),
   widgettype           varchar(50),
   settingurl           varchar(255),
   deleteflag           smallint default 0 comment '0:正常;1:已删除',
   primary key (id)
);
