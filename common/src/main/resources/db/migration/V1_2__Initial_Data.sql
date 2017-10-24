SET FOREIGN_KEY_CHECKS=0;

LOCK TABLES `clients` WRITE;
INSERT INTO `clients` (`id`, `username`, `password`, `generated_password`, `first_name`, `last_name`, `authorities`, `logged_in_at`, `last_active_at`, `access_token`,  `created_at`, `updated_at`)
VALUES
	(1,'client','$2a$04$2i7rplJgBdQzzMEjApaoxOqLv/1JxuYzK62JGWK1tL2KPLWga1ml.',0,'Client', 'Clientyan','CLIENT',NULL,NULL,NULL,NULL,NULL);
UNLOCK TABLES;



SET FOREIGN_KEY_CHECKS=1;
