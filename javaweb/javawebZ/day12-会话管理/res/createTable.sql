/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2022/7/19 8:53:38                            */
/*==============================================================*/


drop table if exists t_item;

drop table if exists t_user;

/*==============================================================*/
/* Table: t_item                                                */
/*==============================================================*/
create table t_item
(
   id                   int(4) not null,
   name                 varchar(60),
   price                int(6),
   pic                  varchar(60),
   primary key (id)
);

alter table t_item comment '商品信息表';

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   account              varchar(40) not null,
   pswd                 varchar(40),
   username             varchar(40),
   primary key (account)
);

alter table t_user comment '会员信息表';

