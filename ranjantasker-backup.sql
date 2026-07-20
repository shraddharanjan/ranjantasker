-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: ranjantasker
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `requester_account`
--

DROP TABLE IF EXISTS `requester_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `requester_account` (
  `user_account_id` int NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `profile_photo` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`user_account_id`),
  CONSTRAINT `user_account_id` FOREIGN KEY (`user_account_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requester_account`
--

LOCK TABLES `requester_account` WRITE;
/*!40000 ALTER TABLE `requester_account` DISABLE KEYS */;
INSERT INTO `requester_account` VALUES (3,NULL,NULL,NULL,NULL,NULL),(5,NULL,NULL,NULL,NULL,NULL),(7,'Shraddha','Ranjan','Crawley','United Kingdom','Shraddha_Kapoor_in_Dhoom_4.jpg'),(9,NULL,NULL,NULL,NULL,NULL),(11,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `requester_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_location`
--

DROP TABLE IF EXISTS `task_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task_location` (
  `id` int NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_location`
--

LOCK TABLES `task_location` WRITE;
/*!40000 ALTER TABLE `task_location` DISABLE KEYS */;
INSERT INTO `task_location` VALUES (1,'Crawley','UK'),(2,'Delhi','India');
/*!40000 ALTER TABLE `task_location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_post`
--

DROP TABLE IF EXISTS `task_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task_post` (
  `task_post_id` int NOT NULL AUTO_INCREMENT,
  `task_description` varchar(10000) DEFAULT NULL,
  `task_title` varchar(255) DEFAULT NULL,
  `task_type` varchar(255) DEFAULT NULL,
  `posted_date` datetime(6) DEFAULT NULL,
  `remote` varchar(255) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  `task_location_id` int DEFAULT NULL,
  `posted_by_id` int DEFAULT NULL,
  PRIMARY KEY (`task_post_id`),
  KEY `posted_by_id_idx` (`posted_by_id`),
  KEY `task_location_id_idx` (`task_location_id`),
  CONSTRAINT `posted_by_id` FOREIGN KEY (`posted_by_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `task_location_id` FOREIGN KEY (`task_location_id`) REFERENCES `task_location` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_post`
--

LOCK TABLES `task_post` WRITE;
/*!40000 ALTER TABLE `task_post` DISABLE KEYS */;
INSERT INTO `task_post` VALUES (1,'Please help me move my sofa!&nbsp;','Move my sofa','Full-time','2024-11-20 20:33:33.520000','Office-Only','20',1,7),(2,'<p>Can someone paint the walls of my room pink please.&nbsp;<img src=\"data:image/jpeg;base64,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\" data-filename=\"room.jpg\" style=\"width: 274px;\"></p>','Paint my walls','Freelance','2024-11-23 13:50:30.736000','Remote-Only','50',2,7);
/*!40000 ALTER TABLE `task_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_seeker_account`
--

DROP TABLE IF EXISTS `task_seeker_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task_seeker_account` (
  `user_account_id` int NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `profile_photo` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `cv` varchar(255) DEFAULT NULL,
  `employment_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_account_id`),
  CONSTRAINT `user_account_id_2` FOREIGN KEY (`user_account_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_seeker_account`
--

LOCK TABLES `task_seeker_account` WRITE;
/*!40000 ALTER TABLE `task_seeker_account` DISABLE KEYS */;
INSERT INTO `task_seeker_account` VALUES (2,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(10,'Shraddha','Ranjan','Shraddha_Kapoor_in_Dhoom_4.jpg','Crawley','United Kingdom','Certificate-12.pdf','Full-Time');
/*!40000 ALTER TABLE `task_seeker_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_seeker_apply`
--

DROP TABLE IF EXISTS `task_seeker_apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task_seeker_apply` (
  `id` int NOT NULL AUTO_INCREMENT,
  `apply_date` datetime(6) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  `cover_letter` varchar(255) DEFAULT NULL,
  `task` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `job_UNIQUE` (`task`),
  CONSTRAINT `task` FOREIGN KEY (`task`) REFERENCES `task_post` (`task_post_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `task_seeker_account` (`user_account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_seeker_apply`
--

LOCK TABLES `task_seeker_apply` WRITE;
/*!40000 ALTER TABLE `task_seeker_apply` DISABLE KEYS */;
INSERT INTO `task_seeker_apply` VALUES (1,'2024-11-23 13:40:40.604000',NULL,NULL,1,10);
/*!40000 ALTER TABLE `task_seeker_apply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_seeker_save`
--

DROP TABLE IF EXISTS `task_seeker_save`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task_seeker_save` (
  `id` int NOT NULL AUTO_INCREMENT,
  `task` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `task_idx` (`task`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `task_1` FOREIGN KEY (`task`) REFERENCES `task_post` (`task_post_id`),
  CONSTRAINT `user_id_1` FOREIGN KEY (`user_id`) REFERENCES `task_seeker_account` (`user_account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_seeker_save`
--

LOCK TABLES `task_seeker_save` WRITE;
/*!40000 ALTER TABLE `task_seeker_save` DISABLE KEYS */;
INSERT INTO `task_seeker_save` VALUES (1,1,10),(2,2,10);
/*!40000 ALTER TABLE `task_seeker_save` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `registration_date` datetime(6) DEFAULT NULL,
  `user_category_id` int DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `user_type_id_idx` (`user_category_id`),
  CONSTRAINT `user_category_id` FOREIGN KEY (`user_category_id`) REFERENCES `users_category` (`user_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'requester@email.com',_binary '','123','2024-11-20 11:21:30.270000',1),(2,'taskseeker@email.com',_binary '','123','2024-11-20 16:13:50.578000',2),(3,'requester1@email.com',_binary '','123','2024-11-20 16:14:07.481000',1),(4,'taskseeker1@email.com',_binary '','$2a$10$6c36PizVh8mVZZBW9tevRemT6XkQyYNLLCfBLvFS17.bLeUustesW','2024-11-20 17:01:21.658000',2),(5,'requester2@email.com',_binary '','$2a$10$N7qC.vyEFd4SGoGRT/bfKu.XXzDHolVdWPfRgf.EBJ0R8o0/L20j2','2024-11-20 17:32:34.817000',1),(6,'taskseeker2@email.com',_binary '','$2a$10$WcYqL.dMc5vHzs0ioUKFOOOamQime5aMyCYJxjoDsiZZONteFpbBq','2024-11-20 17:43:28.885000',2),(7,'requester3@email.com',_binary '','$2a$10$1vAp9lpuPdhFicq4F/1EGuBOboSxsevHneWWPizfdejeyOfoFB.lC','2024-11-20 17:54:14.799000',1),(9,'requester4@email.com',_binary '','$2a$10$PQd5d/c0Nwy7Xohf4QKT8OtJWG5a6/bJSVGc4lBA4v3laFyurYDWq','2024-11-20 17:55:11.813000',1),(10,'taskseeker3@email.com',_binary '','$2a$10$IEiPOXd5GA0bq8sWO00sRuUscKsd3.Ll8/AoOk6QnSZypGrZ6366y','2024-11-20 17:57:32.220000',2),(11,'shraddharanjan@email.com',_binary '','$2a$10$nYGQzhtr1CJ4I8jyYMyaMuyuZgnVT158zZLP/jW3cf1nX0wO4TBSu','2025-08-02 11:54:13.738000',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_category`
--

DROP TABLE IF EXISTS `users_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_category` (
  `user_category_id` int NOT NULL AUTO_INCREMENT,
  `user_category_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_category`
--

LOCK TABLES `users_category` WRITE;
/*!40000 ALTER TABLE `users_category` DISABLE KEYS */;
INSERT INTO `users_category` VALUES (1,'Requester'),(2,'Task Seeker');
/*!40000 ALTER TABLE `users_category` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-07-20 22:33:36
