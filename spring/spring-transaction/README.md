
# create table
```
-- test.staff definition

CREATE TABLE `staff` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`name` varchar(10) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;



-- test.user1 definition

CREATE TABLE `user1` (
`id` int(10) unsigned NOT NULL AUTO_INCREMENT,
`name` varchar(45) NOT NULL DEFAULT '',
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;



-- test.user2 definition

CREATE TABLE `user2` (
`id` int(10) unsigned NOT NULL AUTO_INCREMENT,
`name` varchar(45) NOT NULL DEFAULT '',
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
```
