CREATE TABLE `user` (
`id` bigint(20) NOT NULL COMMENT '主键ID',
`username` varchar(30) DEFAULT NULL COMMENT '姓名',
`password` varchar(10) DEFAULT NULL COMMENT '密码',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;