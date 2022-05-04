# 建库
CREATE DATABASE seata_order;

CREATE DATABASE seata_storage;

CREATE DATABASE seata_account;

# 建表
USE seata_order;

CREATE TABLE t_order (
                         `id` BIGINT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         `user_id` BIGINT(11) DEFAULT NULL COMMENT '用户id',
                         `product_id` BIGINT(11) DEFAULT NULL COMMENT '产品id',
                         `count` INT(11) DEFAULT NULL COMMENT '数量',
                         `money` DECIMAL(11,0) DEFAULT NULL COMMENT '金额',
                         `status` INT(1) DEFAULT NULL COMMENT '订单状态：0：创建中；1：已完结'
) ENGINE=INNODB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

SELECT * FROM t_order;


USE seata_storage;

CREATE TABLE t_storage (
                           `id` BIGINT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
                           `product_id` BIGINT(11) DEFAULT NULL COMMENT '产品id',
                           `total` INT(11) DEFAULT NULL COMMENT '总库存',
                           `used` INT(11) DEFAULT NULL COMMENT '已用库存',
                           `residue` INT(11) DEFAULT NULL COMMENT '剩余库存'
) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


INSERT INTO seata_storage.t_storage(`id`, `product_id`, `total`, `used`, `residue`)
VALUES ('1', '1', '100', '0', '100');

SELECT * FROM t_storage;


USE seata_account;

CREATE TABLE t_account (
                           `id` BIGINT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'id',
                           `user_id` BIGINT(11) DEFAULT NULL COMMENT '用户id',
                           `total` DECIMAL(10,0) DEFAULT NULL COMMENT '总额度',
                           `used` DECIMAL(10,0) DEFAULT NULL COMMENT '已用余额',
                           `residue` DECIMAL(10,0) DEFAULT '0' COMMENT '剩余可用额度'
) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO seata_account.t_account(`id`, `user_id`, `total`, `used`, `residue`)  VALUES ('1', '1', '1000', '0', '1000');

SELECT * FROM t_account;

-- 对于 AT 模式，您必须为您的(每个)业务数据库初始化此 sql。 seata 服务器不需要它。
CREATE TABLE IF NOT EXISTS `undo_log`
(
    `branch_id`     BIGINT       NOT NULL COMMENT 'branch transaction id',
    `xid`           VARCHAR(128) NOT NULL COMMENT 'global transaction id',
    `context`       VARCHAR(128) NOT NULL COMMENT 'undo_log context,such as serialization',
    `rollback_info` LONGBLOB     NOT NULL COMMENT 'rollback info',
    `log_status`    INT(11)      NOT NULL COMMENT '0:normal status,1:defense status',
    `log_created`   DATETIME(6)  NOT NULL COMMENT 'create datetime',
    `log_modified`  DATETIME(6)  NOT NULL COMMENT 'modify datetime',
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4 COMMENT ='AT transaction mode undo table';