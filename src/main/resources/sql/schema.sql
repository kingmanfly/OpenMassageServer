-- 创建数据库
Create DATABASE massagedb;

-- 使用数据库
use massagedb;

create table user(
	`sharp_id` BIGINT not null AUTO_INCREMENT
	COMMENT '用户ID',
	`phone` varchar(16) not null
	comment '手机号码',
	`nickname` varchar(120) not null
	comment '昵称',
	`password` varchar(10) not null
	comment '密码',
	`create_time` TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP
	comment '创建时间',
	`rec_time` TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP
	comment '修改时间', 
	`latitude` double not null DEFAULT -1
	comment '经度',
	`longitude` double not null DEFAULT -1
	comment '维度',
	`category` smallint not null default 1
	comment '类型 ：1 普通用户，2 技师用户',
	`token`  VARCHAR(120) not null
	comment '用户标识',
	PRIMARY KEY (sharp_id),
	KEY idx_create_time(create_time),
	KEY idx_rec_time(rec_time),
	KEY idx_token(token)
	-- AUTO_INCREMENT=1000，自增ID从1000开始
	)
	
	ENGINE = InnoDB
    AUTO_INCREMENT = 1000
    DEFAULT CHARSET = utf8
    COMMENT ='用户表';
    
    
 create table skiller(
    `skiller_id` bigint not null AUTO_INCREMENT
    comment '技师id',
    `phone` varchar(16) not null
	comment '手机号码',
	`pic_head_path` varchar(120) 
	comment '头像路径',
	`pic_show_path` varchar(10000)
	comment '技师展示路径',
	`description` varchar(500)
	comment '描述',
	`age` smallint 
	comment '年龄',
	`height` smallint 
	comment '身高',
	`level` smallint
	comment '等级：1 初级 2 中级 3 高级 4 实习',
	`location` varchar(50)
	comment '位置',
	`create_time` timestamp not null DEFAULT CURRENT_TIMESTAMP
	comment '注册时间',
	`rec_time` timestamp not null DEFAULT CURRENT_TIMESTAMP
	comment '修改时间',
	PRIMARY KEY (skiller_id),
	KEY idx_create_time(create_time),
	KEY idx_rec_time(rec_time)
	-- AUTO_INCREMENT=1000，自增ID从1000开始
 )
    ENGINE = InnoDB
    AUTO_INCREMENT = 1000
    DEFAULT CHARSET = utf8
    COMMENT ='技师表';
    
    
 create table data_statistics(
    `id` bigint not null AUTO_INCREMENT
    comment 'id',
    `content` varchar(120) not null
    comment '技师id或者api',
    `counting` bigint not null default 0
    comment '统计数量',
    `create_time` timestamp not null DEFAULT CURRENT_TIMESTAMP
    comment '创建日期',
    `rec_time` timestamp not null DEFAULT CURRENT_TIMESTAMP
    comment '修改日期',
    `category` smallint not null default 1
    comment '类型： 1 技师 2 api',
    PRIMARY KEY (id),
	KEY idx_create_time(create_time),
	KEY idx_rec_time(rec_time)
	-- AUTO_INCREMENT=1000，自增ID从1000开始
 )
    ENGINE = InnoDB
    AUTO_INCREMENT = 1000
    DEFAULT CHARSET = utf8
    COMMENT ='统计表';

create table vcode(
	    `id` bigint not null AUTO_INCREMENT
	    comment 'id',
	    `phone` varchar(20) not null
	    comment '手机号',
	    `vcode` varchar(4) not null
	    comment '验证码',
	    `expired` timestamp not null
	    comment '过期时间',
	    `create_time` timestamp not null DEFAULT CURRENT_TIMESTAMP
	    comment '创建日期',
	    `rec_time` timestamp not null DEFAULT CURRENT_TIMESTAMP
	    comment '修改日期',
	    PRIMARY KEY (id)
	 )
	    ENGINE = InnoDB
	    DEFAULT CHARSET = utf8
	    COMMENT ='验证码表'; 
	    
	create table leavemessage(
	    `id` bigint not null AUTO_INCREMENT 
	    comment 'id',
	    comefrom varchar(3) 
	    comment '来源',
	    `name` varchar(30) not null 
	    comment '名字',
	    `contact` varchar(20) not null 
	    comment '联系方式',
	    `title` varchar(30) not null
	    comment '标题',
	    `content` varchar(100) not null
	    comment '内容',
	    `create_time` timestamp not null DEFAULT CURRENT_TIMESTAMP
	    comment '创建日期',
	    PRIMARY KEY (id)
	 )
	    ENGINE = InnoDB
	    DEFAULT CHARSET = utf8
	    COMMENT ='留言表';    
	    
    ALTER TABLE skiller
	ADD sharp_id bigint not null;
	

create table authuser(
	    `id` bigint not null AUTO_INCREMENT
	    comment 'id',
	    `username` varchar(20) not null
	    comment '用户名',
	    `nickname` varchar(20) not null
	    comment '昵称',
	    `password` varchar(10) not null
	    comment '密码',
	    `role` smallint default 2
	    comment '1.超级管理员  2.普通管理员 ',
	    `create_time` timestamp not null DEFAULT CURRENT_TIMESTAMP
	    comment '创建日期',
	    `rec_time` timestamp not null DEFAULT CURRENT_TIMESTAMP
	    comment '修改日期',
	    PRIMARY KEY (id)
	 )
	    ENGINE = InnoDB
	    DEFAULT CHARSET = utf8
	    COMMENT ='授权用户表'; 

use massagedb;

ALTER TABLE `user`
ADD CONSTRAINT user_unique_phone UNIQUE (phone);

use massagedb;

ALTER TABLE skiller
ADD sex SMALLINT not null DEFAULT 1 COMMENT '1:女，2,男';
	
	