/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2022/7/30 9:41:39                            */
/*==============================================================*/


drop table if exists t_login_log;

drop table if exists t_user;

/*==============================================================*/
/* Table: t_login_log                                           */
/*==============================================================*/
create table t_login_log
(
   id                   int(4) not null,
   account              varchar(40),
   login_time           datetime,
   primary key (id)
);

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   account              varchar(40) not null,
   username             varchar(60),
   pswd                 varchar(60),
   score                int(4),
   last_login_time      datetime,
   primary key (account)
);

alter table t_login_log add constraint FK_Reference_1 foreign key (account)
      references t_user (account) on delete restrict on update restrict;

