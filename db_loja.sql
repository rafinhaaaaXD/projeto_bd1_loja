-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema db_loja
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db_loja` DEFAULT CHARACTER SET utf8 ;
USE `db_loja` ;

-- -----------------------------------------------------
-- Table `db_loja`.`produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_loja`.`produto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `descricao` TEXT NOT NULL,
  `preco` DECIMAL(10,2) NOT NULL,
  `estoque` INT NOT NULL,
  `categoria_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_produto_categoria`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `db_loja`.`categoria` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `db_loja`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_loja`.`categoria` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `db_loja`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_loja`.`cliente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `telefone` VARCHAR(20) NOT NULL,
  `endereco` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `db_loja`.`venda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_loja`.`venda` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `data` DATE NOT NULL,
  `cliente_id` INT NOT NULL,
  `total` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_venda_cliente`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `db_loja`.`cliente` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `db_loja`.`itemvenda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_loja`.`itemvenda` (
  `venda_id` INT NOT NULL,
  `produto_id` INT NOT NULL,
  `quantidade` INT NOT NULL,
  `preco_unitario` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`venda_id`, `produto_id`),
  CONSTRAINT `fk_itemvenda_venda`
    FOREIGN KEY (`venda_id`)
    REFERENCES `db_loja`.`venda` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_itemvenda_produto`
    FOREIGN KEY (`produto_id`)
    REFERENCES `db_loja`.`produto` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `db_loja`.`fornecedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_loja`.`fornecedor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `contato` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `db_loja`.`produtofornecedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_loja`.`produtofornecedor` (
  `produto_id` INT NOT NULL,
  `fornecedor_id` INT NOT NULL,
  `preco_fornecedor` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`produto_id`, `fornecedor_id`),
  CONSTRAINT `fk_produtofornecedor_produto`
    FOREIGN KEY (`produto_id`)
    REFERENCES `db_loja`.`produto` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_produtofornecedor_fornecedor`
    FOREIGN KEY (`fornecedor_id`)
    REFERENCES `db_loja`.`fornecedor` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- Restaura as configurações anteriores
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
