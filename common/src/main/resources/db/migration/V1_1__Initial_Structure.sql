SET FOREIGN_KEY_CHECKS=0;

CREATE TABLE IF NOT EXISTS `clients` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`username` varchar(255) DEFAULT NULL,
	`password` varchar(255) DEFAULT NULL,
	`generated_password` tinyint(1) NOT NULL DEFAULT '0',
	`first_name` varchar(255) DEFAULT NULL,
	`last_name` varchar(255) DEFAULT NULL,
	`authorities` varchar(255) DEFAULT NULL,
	`logged_in_at` datetime DEFAULT NULL,
	`last_active_at` datetime DEFAULT NULL,
	`access_token` varchar(255) DEFAULT NULL,
	`created_at` datetime DEFAULT NULL,
	`updated_at` datetime DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


SET FOREIGN_KEY_CHECKS=1;
