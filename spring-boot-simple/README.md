随便搞搞能run就行

## 先在127.0.0.1创建2个数据库
```
DROP Database IF EXISTS `test2`;
CREATE Database `test2`;
USE `test2`;
DROP TABLE IF EXISTS `tab`;
CREATE TABLE `tab` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
LOCK TABLES `tab` WRITE;
INSERT INTO `tab` (`id`)
VALUES(1);
UNLOCK TABLES;

DROP Database IF EXISTS `test`;
CREATE Database `test`;
USE test;
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
LOCK TABLES `user` WRITE;
INSERT INTO `user` (`id`)
VALUES(1);
UNLOCK TABLES;
```

*准备工作就完成了可以直接run了，一个空白项目*

### 1.集成freemark
### 2.集成多数据源,starter-jdbc
### 3.多环境配置打包
### 4.等等以后在慢慢集成吧...