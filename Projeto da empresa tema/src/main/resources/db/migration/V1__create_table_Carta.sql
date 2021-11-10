CREATE TABLE IF NOT EXISTS `carta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `descricao` varchar(500) NOT NULL,
  `ataque` int NOT NULL,
  `defesa` int NOT NULL,
  `tipo` varchar(50) NOT NULL,
  `classe` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) 