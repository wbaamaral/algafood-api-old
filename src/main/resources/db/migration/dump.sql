-- MariaDB dump 10.19  Distrib 10.9.2-MariaDB, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: algafood
-- ------------------------------------------------------
-- Server version	5.7.34

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
-- Current Database: `algafood`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `algafood` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `algafood`;

--
-- Table structure for table `cidade`
--

DROP TABLE IF EXISTS `cidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cidade` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `estado_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkworrwk40xj58kevvh3evi500` (`estado_id`),
  CONSTRAINT `FKkworrwk40xj58kevvh3evi500` FOREIGN KEY (`estado_id`) REFERENCES `estado` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cidade`
--

LOCK TABLES `cidade` WRITE;
/*!40000 ALTER TABLE `cidade` DISABLE KEYS */;
INSERT INTO `cidade` VALUES
(1,'Uberlândia',1),
(2,'Belo Horizonte',1),
(3,'São Paulo',2),
(4,'Campinas',2),
(5,'Fortaleza',3),
(6,'Porto Velho',4),
(7,'Ariquemes',4),
(8,'Jaru',4),
(9,'Ji-Paraná',4);
/*!40000 ALTER TABLE `cidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cozinha`
--

DROP TABLE IF EXISTS `cozinha`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cozinha` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cozinha`
--

LOCK TABLES `cozinha` WRITE;
/*!40000 ALTER TABLE `cozinha` DISABLE KEYS */;
INSERT INTO `cozinha` VALUES
(1,'Tailandesa'),
(2,'Indiana'),
(3,'Brasileira');
/*!40000 ALTER TABLE `cozinha` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado`
--

DROP TABLE IF EXISTS `estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estado` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado`
--

LOCK TABLES `estado` WRITE;
/*!40000 ALTER TABLE `estado` DISABLE KEYS */;
INSERT INTO `estado` VALUES
(1,'Minas Gerais'),
(2,'São Paulo'),
(3,'Ceará'),
(4,'Rondônia'),
(5,'Acre'),
(6,'Bahia'),
(8,'Distrito Federal'),
(9,'Amazonas');
/*!40000 ALTER TABLE `estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forma_pagamento`
--

DROP TABLE IF EXISTS `forma_pagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `forma_pagamento` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forma_pagamento`
--

LOCK TABLES `forma_pagamento` WRITE;
/*!40000 ALTER TABLE `forma_pagamento` DISABLE KEYS */;
INSERT INTO `forma_pagamento` VALUES
(1,'Cartão de crédito'),
(2,'Cartão de débito'),
(3,'Dinheiro'),
(4,'Pix');
/*!40000 ALTER TABLE `forma_pagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo`
--

DROP TABLE IF EXISTS `grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grupo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo`
--

LOCK TABLES `grupo` WRITE;
/*!40000 ALTER TABLE `grupo` DISABLE KEYS */;
/*!40000 ALTER TABLE `grupo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo_permissao`
--

DROP TABLE IF EXISTS `grupo_permissao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grupo_permissao` (
  `grupo_id` bigint(20) NOT NULL,
  `permissao_id` bigint(20) NOT NULL,
  KEY `FKh21kiw0y0hxg6birmdf2ef6vy` (`permissao_id`),
  KEY `FKta4si8vh3f4jo3bsslvkscc2m` (`grupo_id`),
  CONSTRAINT `FKh21kiw0y0hxg6birmdf2ef6vy` FOREIGN KEY (`permissao_id`) REFERENCES `permissao` (`id`),
  CONSTRAINT `FKta4si8vh3f4jo3bsslvkscc2m` FOREIGN KEY (`grupo_id`) REFERENCES `grupo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo_permissao`
--

LOCK TABLES `grupo_permissao` WRITE;
/*!40000 ALTER TABLE `grupo_permissao` DISABLE KEYS */;
/*!40000 ALTER TABLE `grupo_permissao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissao`
--

DROP TABLE IF EXISTS `permissao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permissao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissao`
--

LOCK TABLES `permissao` WRITE;
/*!40000 ALTER TABLE `permissao` DISABLE KEYS */;
INSERT INTO `permissao` VALUES
(1,'Permite consultar cozinhas','CONSULTAR_COZINHAS'),
(2,'Permite editar cozinhas','EDITAR_COZINHAS');
/*!40000 ALTER TABLE `permissao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL,
  `descricao` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `preco` decimal(19,2) NOT NULL,
  `restaurante_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb9jhjyghjcn25guim7q4pt8qx` (`restaurante_id`),
  CONSTRAINT `FKb9jhjyghjcn25guim7q4pt8qx` FOREIGN KEY (`restaurante_id`) REFERENCES `restaurante` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES
(1,'','Deliciosa carne suína ao molho especial','Porco com molho agridoce',78.90,1),
(2,'','16 camarões grandes ao molho picante','Camarão tailandês',110.00,1),
(3,'','Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha','Salada picante com carne grelhada',87.20,2),
(4,'','Pão tradicional indiano com cobertura de alho','Garlic Naan',21.00,3),
(5,'','Cubos de frango preparados com molho curry e especiarias','Murg Curry',43.00,3),
(6,'','Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé','Bife Ancho',79.00,4),
(7,'','Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon','T-Bone',89.00,4),
(8,'','Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese','Sanduíche X-Tudo',19.00,5),
(9,'','Acompanha farinha, mandioca e vinagrete','Espetinho de Cupim',8.00,6);
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurante`
--

DROP TABLE IF EXISTS `restaurante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restaurante` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_atualizacao` datetime NOT NULL,
  `data_cadastro` datetime NOT NULL,
  `endereco_bairro` varchar(255) DEFAULT NULL,
  `endereco_cep` varchar(255) DEFAULT NULL,
  `endereco_complemento` varchar(255) DEFAULT NULL,
  `endereco_logradouro` varchar(255) DEFAULT NULL,
  `endereco_numero` varchar(255) DEFAULT NULL,
  `nome` varchar(255) NOT NULL,
  `taxa_frete` decimal(19,2) NOT NULL,
  `cozinha_id` bigint(20) NOT NULL,
  `endereco_cidade_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK76grk4roudh659skcgbnanthi` (`cozinha_id`),
  KEY `FKbc0tm7hnvc96d8e7e2ulb05yw` (`endereco_cidade_id`),
  CONSTRAINT `FK76grk4roudh659skcgbnanthi` FOREIGN KEY (`cozinha_id`) REFERENCES `cozinha` (`id`),
  CONSTRAINT `FKbc0tm7hnvc96d8e7e2ulb05yw` FOREIGN KEY (`endereco_cidade_id`) REFERENCES `cidade` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurante`
--

LOCK TABLES `restaurante` WRITE;
/*!40000 ALTER TABLE `restaurante` DISABLE KEYS */;
INSERT INTO `restaurante` VALUES
(1,'2022-10-01 13:21:35','2022-10-01 13:21:35','Centro','38400-999',NULL,'Rua João Pinheiro','1000','Thai Gourmet',10.00,1,1),
(2,'2022-10-01 13:21:35','2022-10-01 13:21:35',NULL,NULL,NULL,NULL,NULL,'Thai Delivery',9.50,1,NULL),
(3,'2022-10-01 13:21:35','2022-10-01 13:21:35',NULL,NULL,NULL,NULL,NULL,'Tuk Tuk Comida Indiana',15.00,2,NULL),
(4,'2022-10-01 13:21:35','2022-10-01 13:21:35',NULL,NULL,NULL,NULL,NULL,'Fogão de Lenha',8.50,3,NULL),
(5,'2022-10-01 13:21:35','2022-10-01 13:21:35',NULL,NULL,NULL,NULL,NULL,'Panela Mineira',0.00,3,NULL),
(6,'2022-10-01 13:21:35','2022-10-01 13:21:35',NULL,NULL,NULL,NULL,NULL,'Panela Mineira',15.00,3,NULL),
(7,'2022-10-01 13:21:35','2022-10-01 13:21:35',NULL,NULL,NULL,NULL,NULL,'Panela Brasil',0.00,3,NULL),
(8,'2022-10-01 13:21:35','2022-10-01 13:21:35',NULL,NULL,NULL,NULL,NULL,'Panela Mineira 2',0.00,3,NULL),
(9,'2022-10-01 13:21:35','2022-10-01 13:21:35',NULL,NULL,NULL,NULL,NULL,'Rei do rango',0.00,3,NULL);
/*!40000 ALTER TABLE `restaurante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurante_forma_pagamento`
--

DROP TABLE IF EXISTS `restaurante_forma_pagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restaurante_forma_pagamento` (
  `restaurante_id` bigint(20) NOT NULL,
  `forma_pagamento_id` bigint(20) NOT NULL,
  KEY `FK7aln770m80358y4olr03hyhh2` (`forma_pagamento_id`),
  KEY `FKa30vowfejemkw7whjvr8pryvj` (`restaurante_id`),
  CONSTRAINT `FK7aln770m80358y4olr03hyhh2` FOREIGN KEY (`forma_pagamento_id`) REFERENCES `forma_pagamento` (`id`),
  CONSTRAINT `FKa30vowfejemkw7whjvr8pryvj` FOREIGN KEY (`restaurante_id`) REFERENCES `restaurante` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurante_forma_pagamento`
--

LOCK TABLES `restaurante_forma_pagamento` WRITE;
/*!40000 ALTER TABLE `restaurante_forma_pagamento` DISABLE KEYS */;
INSERT INTO `restaurante_forma_pagamento` VALUES
(1,1),
(1,2),
(1,3),
(2,3),
(3,2),
(3,3);
/*!40000 ALTER TABLE `restaurante_forma_pagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_cadastro` datetime NOT NULL,
  `email` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_grupo`
--

DROP TABLE IF EXISTS `usuario_grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario_grupo` (
  `usuario_id` bigint(20) NOT NULL,
  `grupo_id` bigint(20) NOT NULL,
  KEY `FKk30suuy31cq5u36m9am4om9ju` (`grupo_id`),
  KEY `FKdofo9es0esuiahyw2q467crxw` (`usuario_id`),
  CONSTRAINT `FKdofo9es0esuiahyw2q467crxw` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FKk30suuy31cq5u36m9am4om9ju` FOREIGN KEY (`grupo_id`) REFERENCES `grupo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_grupo`
--

LOCK TABLES `usuario_grupo` WRITE;
/*!40000 ALTER TABLE `usuario_grupo` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario_grupo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-01 11:12:09
