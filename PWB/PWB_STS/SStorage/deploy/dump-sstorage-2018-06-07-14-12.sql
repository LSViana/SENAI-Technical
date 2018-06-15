-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema sstorage
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sstorage
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sstorage` DEFAULT CHARACTER SET utf8 ;
USE `sstorage` ;

-- -----------------------------------------------------
-- Table `sstorage`.`environment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sstorage`.`environment` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(40) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sstorage`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sstorage`.`user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(64) NOT NULL,
  `firstName` VARCHAR(20) NOT NULL,
  `lastName` VARCHAR(40) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `type` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_ob8kqyqqgmefl0aco34akdtpe` (`email` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sstorage`.`patrimonycategory`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sstorage`.`patrimonycategory` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_gvbiyydbeqeav4ka07upetdc2` (`name` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sstorage`.`patrimony`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sstorage`.`patrimony` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `datetime` DATETIME NOT NULL,
  `imageB64` LONGTEXT NOT NULL,
  `name` VARCHAR(40) NOT NULL,
  `id_patrimony_category` BIGINT(20) NOT NULL,
  `id_user` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK9tre8tjfrinp7hqduqay7irym` (`id_patrimony_category` ASC),
  INDEX `FK4dsw1sff2y6vb8h36wiincwaf` (`id_user` ASC),
  CONSTRAINT `FK4dsw1sff2y6vb8h36wiincwaf`
    FOREIGN KEY (`id_user`)
    REFERENCES `sstorage`.`user` (`id`),
  CONSTRAINT `FK9tre8tjfrinp7hqduqay7irym`
    FOREIGN KEY (`id_patrimony_category`)
    REFERENCES `sstorage`.`patrimonycategory` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sstorage`.`patrimony_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sstorage`.`patrimony_item` (
  `id` BIGINT(20) NOT NULL,
  `lastMovement` DATETIME NOT NULL,
  `state` INT(11) NOT NULL,
  `id_environment` BIGINT(20) NOT NULL,
  `id_patrimony` BIGINT(20) NOT NULL,
  `id_user` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK4c8kaerlxb52pd2gwrhysmkmw` (`id_environment` ASC),
  INDEX `FKocrmvth0csvcp94iq09whcet5` (`id_patrimony` ASC),
  INDEX `FKl1k8wjcrh6cide4woq3wxgyeb` (`id_user` ASC),
  CONSTRAINT `FK4c8kaerlxb52pd2gwrhysmkmw`
    FOREIGN KEY (`id_environment`)
    REFERENCES `sstorage`.`environment` (`id`),
  CONSTRAINT `FKl1k8wjcrh6cide4woq3wxgyeb`
    FOREIGN KEY (`id_user`)
    REFERENCES `sstorage`.`user` (`id`),
  CONSTRAINT `FKocrmvth0csvcp94iq09whcet5`
    FOREIGN KEY (`id_patrimony`)
    REFERENCES `sstorage`.`patrimony` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sstorage`.`movement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sstorage`.`movement` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `dateTime` DATETIME NOT NULL,
  `id_destinyEnvironment` BIGINT(20) NOT NULL,
  `id_originEnvironment` BIGINT(20) NOT NULL,
  `id_patrimony_item` BIGINT(20) NOT NULL,
  `id_user` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK4oayyjr8pwcrm361n9x0aaekf` (`id_destinyEnvironment` ASC),
  INDEX `FK1mpnf5hptpy8hailvw2p31gy` (`id_originEnvironment` ASC),
  INDEX `FKbl7hnudaeruvmlfdxn8munka0` (`id_patrimony_item` ASC),
  INDEX `FK8d5v5h6mvdd76j5tao3n5nswi` (`id_user` ASC),
  CONSTRAINT `FK1mpnf5hptpy8hailvw2p31gy`
    FOREIGN KEY (`id_originEnvironment`)
    REFERENCES `sstorage`.`environment` (`id`),
  CONSTRAINT `FK4oayyjr8pwcrm361n9x0aaekf`
    FOREIGN KEY (`id_destinyEnvironment`)
    REFERENCES `sstorage`.`environment` (`id`),
  CONSTRAINT `FK8d5v5h6mvdd76j5tao3n5nswi`
    FOREIGN KEY (`id_user`)
    REFERENCES `sstorage`.`user` (`id`),
  CONSTRAINT `FKbl7hnudaeruvmlfdxn8munka0`
    FOREIGN KEY (`id_patrimony_item`)
    REFERENCES `sstorage`.`patrimony_item` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
