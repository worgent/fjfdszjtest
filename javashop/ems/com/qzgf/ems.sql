
/*菜单*/
CREATE TABLE `es_ems_site_menu` (                                            
                `menuid` mediumint(8) NOT NULL auto_increment,                         
                `parentid` mediumint(8) default NULL,                                  
                `name` varchar(50) collate utf8_bin default NULL,                      
                `url` varchar(255) collate utf8_bin default NULL,                      
                `sort` int(11) default NULL,                                           
                PRIMARY KEY  (`menuid`)                                                
              ) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ;
 
/*版权信息主表*/             
CREATE TABLE `es_ems_article_cat` (                                             
                  `cat_id` mediumint(8) NOT NULL auto_increment,                            
                  `name` varchar(200) collate utf8_bin default NULL,                        
                  `parent_id` mediumint(8) default NULL,                                    
                  `cat_path` varchar(200) collate utf8_bin default NULL,                    
                  `cat_order` smallint(5) default NULL,                                     
                  PRIMARY KEY  (`cat_id`)                                                   
                ) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COLLATE=utf8_bin  ;
                
/*版权信息子表*/  
CREATE TABLE `es_ems_article` (                                              
              `id` mediumint(8) NOT NULL auto_increment,                             
              `title` varchar(255) collate utf8_bin default NULL,                    
              `content` longtext collate utf8_bin,                                   
              `create_time` bigint(20) default NULL,                                 
              `cat_id` mediumint(8) default NULL,                                    
              PRIMARY KEY  (`id`)                                                    
            ) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COLLATE=utf8_bin  ;
            
            
CREATE TABLE `es_ems_goods` (                                                                             
            `goods_id` mediumint(8) NOT NULL auto_increment,                                                    
            `name` varchar(200) collate utf8_bin default NULL,                                                  
            `sn` varchar(200) collate utf8_bin default NULL,                                                    
            `brand_id` mediumint(8) default NULL,                                                               
            `cat_id` mediumint(8) default NULL,                                                                 
            `type_id` mediumint(8) default NULL,                                                                
            `goods_type` enum('normal','bind') collate utf8_bin default 'normal',                               
            `unit` varchar(20) collate utf8_bin default NULL,                                                   
            `weight` decimal(20,3) default NULL,                                                                
            `market_enable` smallint(1) default NULL COMMENT '0为未上架\n            1为上架了\n            ',  
            `image_default` longtext collate utf8_bin COMMENT '用于显示在列表上的',                             
            `image_file` longtext collate utf8_bin COMMENT '商品相册中的图片',                                  
            `brief` varchar(255) collate utf8_bin default NULL,                                                 
            `intro` longtext collate utf8_bin,                                                                  
            `price` decimal(20,3) default NULL,                                                                 
            `cost` decimal(20,3) default NULL,                                                                  
            `mktprice` decimal(20,3) default NULL,                                                              
            `params` longtext collate utf8_bin,                                                                 
            `specs` longtext collate utf8_bin,                                                                  
            `have_spec` smallint(1) default NULL,                                                               
            `adjuncts` longtext collate utf8_bin,                                                               
            `create_time` bigint(8) default NULL,                                                               
            `last_modify` bigint(8) default NULL,                                                               
            `view_count` int(10) default NULL,                                                                  
            `buy_count` int(10) default NULL,                                                                   
            `disabled` smallint(1) default NULL,                                                                
            `store` mediumint(8) default NULL,                                                                  
            `point` int(10) default NULL,                                                                       
            `page_title` varchar(255) collate utf8_bin default NULL,                                            
            `meta_keywords` varchar(1000) collate utf8_bin default NULL,                                        
            `meta_description` varchar(1000) collate utf8_bin default NULL,                                     
            `p20` varchar(255) collate utf8_bin default NULL,                                                   
            `p19` varchar(255) collate utf8_bin default NULL,                                                   
            `p18` varchar(255) collate utf8_bin default NULL,                                                   
            `p17` varchar(255) collate utf8_bin default NULL,                                                   
            `p16` varchar(255) collate utf8_bin default NULL,                                                   
            `p15` varchar(255) collate utf8_bin default NULL,                                                   
            `p14` varchar(255) collate utf8_bin default NULL,                                                   
            `p13` varchar(255) collate utf8_bin default NULL,                                                   
            `p12` varchar(255) collate utf8_bin default NULL,                                                   
            `p11` varchar(255) collate utf8_bin default NULL,                                                   
            `p10` varchar(255) collate utf8_bin default NULL,                                                   
            `p9` varchar(255) collate utf8_bin default NULL,                                                    
            `p8` varchar(255) collate utf8_bin default NULL,                                                    
            `p7` varchar(255) collate utf8_bin default NULL,                                                    
            `p6` varchar(255) collate utf8_bin default NULL,                                                    
            `p5` varchar(255) collate utf8_bin default NULL,                                                    
            `p4` varchar(255) collate utf8_bin default NULL,                                                    
            `p3` varchar(255) collate utf8_bin default NULL,                                                    
            `p2` varchar(255) collate utf8_bin default NULL,                                                    
            `p1` varchar(255) collate utf8_bin default NULL,                                                    
            PRIMARY KEY  (`goods_id`),                                                                          
            KEY `goods_cat_id` (`cat_id`),                                                                      
            KEY `gppds_brand_id` (`brand_id`),                                                                  
            KEY `goods_name` (`name`),                                                                          
            KEY `goods_sn` (`sn`)                                                                               
          ) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_bin          ;
          
          
CREATE TABLE `es_ems_goods_cat` (                                            
                `cat_id` mediumint(8) NOT NULL auto_increment,                         
                `name` varchar(200) collate utf8_bin default NULL,                     
                `parent_id` mediumint(8) default NULL,                                 
                `cat_path` varchar(200) collate utf8_bin default NULL,                 
                `goods_count` mediumint(8) default NULL,                               
                `cat_order` smallint(5) default NULL,                                  
                `type_id` mediumint(8) default NULL,                                   
                `list_show` enum('0','1') collate utf8_bin default '1',                
                `image` varchar(255) collate utf8_bin default NULL,                    
                PRIMARY KEY  (`cat_id`)                                                
              ) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COLLATE=utf8_bin  ;
                               
  CREATE TABLE `es_ems_goods_type` (                                                                                                                                                               
                 `type_id` mediumint(8) NOT NULL auto_increment,                                                                                                                                            
                 `name` varchar(100) collate utf8_bin default NULL,                                                                                                                                         
                 `props` longtext collate utf8_bin COMMENT '1输入项 可搜索 \n            2输入项 不可搜索\n            3选择项 渐进式搜索 \n            4选择项 普通搜索 \n            5选择项 不可搜索 ',  
                 `params` longtext collate utf8_bin,                                                                                                                                                        
                 `disabled` smallint(1) default NULL,                                                                                                                                                       
                 `is_physical` smallint(1) default NULL,                                                                                                                                                    
                 `have_prop` smallint(1) default NULL COMMENT '1是\n            0否',                                                                                                                       
                 `have_parm` smallint(1) default NULL COMMENT '1是\n            0否',                                                                                                                       
                 `join_brand` smallint(1) default NULL COMMENT '1是\n            0否',                                                                                                                      
                 PRIMARY KEY  (`type_id`)                                                                                                                                                                   
               ) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin   ;                                                                                                                    
            
                   
             