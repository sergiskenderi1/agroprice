CREATE DATABASE  IF NOT EXISTS `agroprice` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `agroprice`;
-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: agroprice
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `njesimatese`
--

DROP TABLE IF EXISTS `njesimatese`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `njesimatese` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `emri` varchar(45) NOT NULL,
  `pershkrim` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `produkt`
--

DROP TABLE IF EXISTS `produkt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `produkt` (
  `idprodukt` int(11) NOT NULL AUTO_INCREMENT,
  `emri` varchar(45) NOT NULL,
  `cmimiMin` float NOT NULL,
  `valid` tinyint(4) NOT NULL,
  `cmimiMax` float NOT NULL,
  `idnjesimatese` int(11) NOT NULL,
  PRIMARY KEY (`idprodukt`),
  KEY `idnjesimatese_idx` (`idnjesimatese`),
  CONSTRAINT `idnjesimatese` FOREIGN KEY (`idnjesimatese`) REFERENCES `njesimatese` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `produktnetreg`
--

DROP TABLE IF EXISTS `produktnetreg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `produktnetreg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sasiaNeTreg` int(11) NOT NULL,
  `valid` tinyint(4) NOT NULL,
  `idshites` int(11) NOT NULL,
  `idProdukt` int(11) NOT NULL,
  `treguId` int(11) NOT NULL,
  `cmimiShites` float NOT NULL,
  `pershkrimi` varchar(245) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idshites_idx` (`idshites`),
  KEY `idProdukt_idx` (`idProdukt`),
  KEY `treguId_idx` (`treguId`),
  CONSTRAINT `idProdukt` FOREIGN KEY (`idProdukt`) REFERENCES `produkt` (`idprodukt`),
  CONSTRAINT `idshites` FOREIGN KEY (`idshites`) REFERENCES `user` (`iduseri`),
  CONSTRAINT `treguId` FOREIGN KEY (`treguId`) REFERENCES `tregu` (`idtregu`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `produktrezervuar`
--

DROP TABLE IF EXISTS `produktrezervuar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `produktrezervuar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sasia` int(11) NOT NULL,
  `cmimi` float NOT NULL,
  `valid` tinyint(4) NOT NULL,
  `id_rezervim` int(11) NOT NULL,
  `id_produktnetreg` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_rezervim_idx` (`id_rezervim`),
  KEY `id_produktnetreg_idx` (`id_produktnetreg`),
  CONSTRAINT `id_produktnetreg` FOREIGN KEY (`id_produktnetreg`) REFERENCES `produktnetreg` (`id`),
  CONSTRAINT `id_rezervim` FOREIGN KEY (`id_rezervim`) REFERENCES `rezervim` (`idrezervim`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rezervim`
--

DROP TABLE IF EXISTS `rezervim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `rezervim` (
  `idrezervim` int(11) NOT NULL AUTO_INCREMENT,
  `data` varchar(45) DEFAULT NULL,
  `mesazhi` varchar(245) DEFAULT NULL,
  `cmimiTotal` float DEFAULT NULL,
  `valid` tinyint(4) NOT NULL,
  `idclient` int(11) NOT NULL,
  `idstatus` int(11) NOT NULL,
  `idShites` int(11) NOT NULL,
  PRIMARY KEY (`idrezervim`),
  KEY `idclient_idx` (`idclient`),
  KEY `idstatus_idx` (`idstatus`),
  KEY `idShites_idx` (`idShites`),
  KEY `shites_id_idx` (`idShites`),
  CONSTRAINT `idclient` FOREIGN KEY (`idclient`) REFERENCES `user` (`iduseri`),
  CONSTRAINT `idstatus` FOREIGN KEY (`idstatus`) REFERENCES `status` (`id`),
  CONSTRAINT `shites_id` FOREIGN KEY (`idShites`) REFERENCES `user` (`iduseri`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roli`
--

DROP TABLE IF EXISTS `roli`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `roli` (
  `idroli` int(11) NOT NULL AUTO_INCREMENT,
  `emri` varchar(45) NOT NULL,
  `pershkrim` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idroli`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `emri` varchar(45) NOT NULL,
  `pershkrim` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tregu`
--

DROP TABLE IF EXISTS `tregu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tregu` (
  `idtregu` int(11) NOT NULL AUTO_INCREMENT,
  `emri` varchar(45) NOT NULL,
  `adresa` varchar(45) NOT NULL,
  `celular` varchar(45) NOT NULL,
  `valid` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`idtregu`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `iduseri` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `fjalekalimi` varchar(45) NOT NULL,
  `emri` varchar(45) NOT NULL,
  `mbiemri` varchar(45) NOT NULL,
  `adresa` varchar(45) NOT NULL,
  `celular` varchar(45) NOT NULL,
  `id_tregu` int(11) DEFAULT NULL,
  `roli` int(11) NOT NULL,
  `valid` tinyint(4) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`iduseri`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `id_tregu_idx` (`id_tregu`),
  KEY `roli_idx` (`roli`),
  CONSTRAINT `id_tregu` FOREIGN KEY (`id_tregu`) REFERENCES `tregu` (`idtregu`),
  CONSTRAINT `roli` FOREIGN KEY (`roli`) REFERENCES `roli` (`idroli`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'agroprice'
--

--
-- Dumping routines for database 'agroprice'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-10 12:06:09
