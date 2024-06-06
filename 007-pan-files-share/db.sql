CREATE TABLE `pan_file`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT,
    `file_id`        varchar(128) DEFAULT NULL,
    `file_name`      varchar(256) DEFAULT NULL,
    `dir`            tinyint(4)   DEFAULT NULL,
    `parent_file_id` varchar(128) DEFAULT NULL,
    `path`           text,
    `create_time`    datetime     DEFAULT NULL,
    `update_time`    datetime     DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;