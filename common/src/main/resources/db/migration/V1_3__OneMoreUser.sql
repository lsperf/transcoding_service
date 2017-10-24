SET FOREIGN_KEY_CHECKS=0;

LOCK TABLES `clients` WRITE;
INSERT INTO `clients` (`id`, `username`, `password`, `generated_password`, `first_name`, `last_name`, `authorities`, `logged_in_at`, `last_active_at`, `access_token`,  `created_at`, `updated_at`)
VALUES
	(2,'levon','$2a$04$jzrul.CCugvb4fFkxEdDCOSDjbNqNyshxRPlNHbpRX1/T42L5qAGq',0,'Client', 'Clientyan','CLIENT',NULL,NULL,NULL,NULL,NULL);
UNLOCK TABLES;



SET FOREIGN_KEY_CHECKS=1;
