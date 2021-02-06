-- MariaDB dump 10.18  Distrib 10.5.8-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: chinese_checkers
-- ------------------------------------------------------
-- Server version	10.5.8-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `games`
--

DROP TABLE IF EXISTS `games`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `games` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `players` int(11) NOT NULL,
  `counters` int(11) NOT NULL,
  `fields` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `games`
--

LOCK TABLES `games` WRITE;
/*!40000 ALTER TABLE `games` DISABLE KEYS */;
INSERT INTO `games` VALUES (1,2,3,4),(2,3,4,5),(3,2,10,4),(4,2,10,4),(5,2,10,4),(6,2,10,4),(7,2,10,4),(8,3,6,3),(9,2,1,4),(10,2,10,4),(11,2,3,2);
/*!40000 ALTER TABLE `games` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `moves`
--

DROP TABLE IF EXISTS `moves`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `moves` (
  `game_id` int(11) NOT NULL,
  `move_nr` int(11) NOT NULL,
  `fromX` int(11) NOT NULL,
  `fromY` int(11) NOT NULL,
  `toX` int(11) NOT NULL,
  `toY` int(11) NOT NULL,
  PRIMARY KEY (`game_id`,`move_nr`),
  CONSTRAINT `moves_ibfk_1` FOREIGN KEY (`game_id`) REFERENCES `games` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moves`
--

LOCK TABLES `moves` WRITE;
/*!40000 ALTER TABLE `moves` DISABLE KEYS */;
INSERT INTO `moves` VALUES (1,1,1,1,2,2),(1,2,2,3,4,5),(1,3,1,2,3,4),(1,4,1,2,3,4),(2,1,2,44,4,5),(2,2,12,44,3,1),(4,1,1,1,1,1),(4,2,1,1,1,1),(4,3,1,1,1,1),(4,4,1,1,1,1),(4,5,1,1,1,1),(7,1,13,7,12,8),(7,2,3,7,4,8),(7,3,13,6,12,6),(7,4,3,5,4,6),(7,5,13,5,11,6),(8,1,9,2,8,3),(8,2,8,8,7,7),(8,3,2,5,3,5),(8,4,8,2,7,2),(8,5,7,8,7,6),(8,6,2,4,3,4),(8,7,8,3,7,3),(8,8,8,9,8,8),(8,9,3,4,4,4),(8,10,7,3,6,4),(8,11,8,8,6,7),(8,12,6,7,8,6),(8,13,1,4,2,4),(8,14,7,1,7,3),(8,15,7,3,5,4),(8,16,9,7,8,7),(8,17,2,4,3,4),(8,18,9,0,7,1),(8,19,7,1,7,3),(8,20,9,8,9,7),(8,21,3,4,5,3),(8,22,5,3,7,4),(8,23,6,4,4,5),(8,24,8,6,7,5),(8,25,4,4,6,5),(8,26,6,5,8,6),(8,27,7,2,6,3),(8,28,8,7,6,6),(8,29,6,6,8,5),(8,30,8,5,6,4),(8,31,6,4,6,2),(8,32,1,5,2,5),(8,33,8,1,7,1),(8,34,7,6,6,6),(8,35,2,5,4,6),(8,36,4,6,4,4),(8,37,4,4,6,5),(8,38,6,5,8,4),(8,39,5,4,5,5),(8,40,9,7,8,7),(8,41,3,5,5,4),(8,42,5,4,5,6),(8,43,5,6,5,4),(8,44,5,5,5,6),(8,45,7,5,6,5),(8,46,8,4,9,3),(8,47,6,3,5,3),(8,48,8,7,8,5),(8,49,8,5,6,4),(8,50,6,4,4,3),(8,51,0,5,1,5),(8,52,5,6,4,7),(8,53,6,5,4,4),(8,54,4,4,4,2),(8,55,2,6,3,6),(8,56,5,3,5,5),(8,57,7,7,7,6),(8,58,5,4,5,6),(8,59,5,6,7,5),(8,60,7,5,9,6),(8,61,9,1,8,2),(8,62,9,9,8,9),(8,63,9,3,10,4),(8,64,5,5,5,6),(8,65,7,6,5,5),(8,66,5,5,3,4),(8,67,7,4,8,5),(8,68,7,1,7,2),(8,69,3,4,3,3),(8,70,3,6,5,7),(8,71,5,6,5,8),(8,72,8,9,7,8),(8,73,10,4,11,4),(8,74,5,8,4,9),(8,75,4,2,3,1),(8,76,11,4,12,5),(8,77,7,2,7,4),(8,78,6,2,5,1),(8,79,9,6,10,6),(8,80,7,4,6,5),(8,81,6,6,6,4),(8,82,1,5,2,6),(8,83,7,3,5,4),(8,84,5,4,3,5),(8,85,7,8,6,8),(8,86,2,6,3,6),(8,87,6,5,5,5),(8,88,6,8,6,7),(8,89,5,7,7,6),(8,90,7,6,9,5),(8,91,3,5,3,7),(8,92,6,4,5,3),(8,93,8,6,10,5),(8,94,8,2,8,3),(8,95,5,3,3,2),(8,96,3,2,3,0),(8,97,3,6,5,7),(8,98,5,7,7,6),(8,99,4,5,4,6),(8,100,6,7,5,6),(8,101,10,6,11,5),(8,102,8,3,8,4),(8,103,5,6,5,4),(8,104,9,5,11,4),(8,105,5,5,3,6),(8,106,3,6,3,8),(8,107,5,4,5,3),(8,108,7,6,8,6),(8,109,8,4,7,4),(8,110,5,3,3,2),(8,111,8,5,9,4),(8,112,4,6,4,8),(8,113,4,3,4,2),(8,114,8,6,9,6),(8,115,7,4,6,5),(8,116,3,3,4,3),(8,117,9,4,10,4),(8,118,3,7,3,9),(8,119,4,3,4,1),(8,120,9,6,10,6),(8,121,6,5,5,5),(8,122,5,5,4,6),(8,123,4,6,3,6),(8,124,3,6,3,7),(8,125,4,7,5,7),(8,126,5,7,5,8),(9,1,16,6,15,5),(9,2,0,6,1,6),(9,3,1,6,2,7),(9,4,2,7,3,7),(9,5,3,7,4,8),(9,6,4,8,5,8),(9,7,5,8,6,9),(9,8,6,9,7,9),(10,1,3,6,4,7),(10,2,13,6,12,6),(10,3,3,7,5,6),(10,4,13,7,12,8),(10,5,3,4,4,5),(10,6,12,6,11,5),(10,7,4,7,5,7),(10,8,12,8,11,8),(11,1,7,3,6,4),(11,2,1,2,2,3),(11,3,6,4,5,3),(11,4,1,3,3,2),(11,5,7,2,6,2),(11,6,2,3,3,3),(11,7,6,2,5,1),(11,8,3,3,4,4),(11,9,5,3,3,4),(11,10,3,4,2,4),(11,11,2,4,1,3);
/*!40000 ALTER TABLE `moves` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`seba`@`localhost`*/ /*!50003 trigger insert_move_bef
    before insert
    on moves
    for each row
begin
    set new.move_nr = (select coalesce(max(move_nr) + 1, 1) from moves where game_id = new.game_id);
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-06 11:09:06
