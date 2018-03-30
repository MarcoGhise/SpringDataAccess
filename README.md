### MySql ###

Configure the local MySql instance and chanhe the configuration in the application.properties file. 

#### Sql Ddl ####

```sql
CREATE DATABASE `registry`

CREATE TABLE `user` (
  `name` varchar(50) DEFAULT NULL,
  `surname` varchar(50) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getUserByAge`(i_age int)
BEGIN

		SELECT id, name, surname, age FROM user WHERE age = i_age;

END$$
DELIMITER ;
```
