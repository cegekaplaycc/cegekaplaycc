# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table horse (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_horse primary key (id))
;

create table race (
  id                        bigint not null,
  constraint pk_race primary key (id))
;

create table user (
  id                        bigint not null,
  credits                   bigint not null,
  name                      varchar(255),
  constraint pk_user primary key (id, credits))
;

create sequence horse_seq;

create sequence race_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists horse;

drop table if exists race;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists horse_seq;

drop sequence if exists race_seq;

drop sequence if exists user_seq;

