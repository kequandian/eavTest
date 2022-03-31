SET
FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- eav测试用示例表
-- ----------------------------
drop table if exists eav_test;
create table eav_test
(
    id     bigint(20) not null auto_increment comment 'id',
    name   varchar(50) not null comment '名字',
    org_id bigint(20) default null comment '隔离id',
    primary key (id)
) engine=innodb;

INSERT INTO `t_eav_entity` (`entity_name`, `table_name`, `org_id`, `org_tag`, `name`, `note`)
VALUES ('Test', 'eav_test', '100000000000000010', NULL, '测试实体', NULL);