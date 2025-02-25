CREATE DATABASE  IF NOT EXISTS `furnitureshop` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `furnitureshop`;
-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: furnitureshop
-- ------------------------------------------------------
-- Server version	8.0.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `orderdetails`
--

DROP TABLE IF EXISTS `orderdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderdetails` (
  `orderdetailid` int NOT NULL AUTO_INCREMENT,
  `porderno` varchar(45) DEFAULT NULL,
  `productno` varchar(45) DEFAULT NULL,
  `productname` varchar(45) DEFAULT NULL,
  `amount` int DEFAULT NULL,
  `sum` int DEFAULT NULL,
  PRIMARY KEY (`orderdetailid`),
  KEY `detailsporder` (`porderno`),
  CONSTRAINT `detailsporder` FOREIGN KEY (`porderno`) REFERENCES `porder` (`porderno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdetails`
--

LOCK TABLES `orderdetails` WRITE;
/*!40000 ALTER TABLE `orderdetails` DISABLE KEYS */;
INSERT INTO `orderdetails` VALUES (63,'o002','p001','blahaj 鯊魚玩偶',2,1398),(64,'o002','p002','jordkastanj 靠枕',2,138),(65,'o002','p004','huvudroll 肉丸',3,1197),(77,'o003','p003','friheten 轉角沙發床',3,38970),(78,'o003','p001','blahaj 鯊魚玩偶',17,11883),(79,'o001','p001','blahaj 鯊魚玩偶',2,1398),(82,'o004','p006','malm 雙人床框',2,15998),(83,'o004','p007','samla 覆蓋收納盒',1,579),(92,'o009','p001','blahaj 鯊魚玩偶',2,1398),(94,'o010','p004','huvudroll 肉丸',1,399),(95,'o010','p007','samla 覆蓋收納盒',1,579),(96,'o011','p001','blahaj 鯊魚玩偶',2,1398),(97,'o011','p007','samla 覆蓋收納盒',2,1158),(98,'o011','p004','huvudroll 肉丸',1,399),(99,'o012','p004','huvudroll 肉丸',2,798),(100,'o012','p005','djungelskog 棕熊玩偶',3,2697),(101,'o006','p001','blahaj 鯊魚玩偶',2,1398),(102,'o006','p007','samla 覆蓋收納盒',2,1158),(103,'o006','p006','malm 雙人床框',1,7999);
/*!40000 ALTER TABLE `orderdetails` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-25 11:38:09
