# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table restaurant (
  id                        varchar(255) not null,
  name                      varchar(255),
  constraint pk_restaurant primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table restaurant;

SET FOREIGN_KEY_CHECKS=1;

