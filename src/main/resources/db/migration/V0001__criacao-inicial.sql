-- -----------------------------------------------------
-- Wélyqrson B. Amaral
-- -----------------------------------------------------


USE algafood;

-- -----------------------------------------------------
-- Table cozinha
-- -----------------------------------------------------
DROP TABLE IF EXISTS algafood.cozinha;

CREATE TABLE IF NOT EXISTS algafood.cozinha (
  id BIGINT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(60) NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;
