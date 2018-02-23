-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema board_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema board_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `board_db` DEFAULT CHARACTER SET utf8 ;
USE `board_db` ;

-- -----------------------------------------------------
-- Table `board_db`.`board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `board_db`.`board` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(200) NOT NULL,
  `content` TEXT NULL DEFAULT NULL,
  `Date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `userId` INT(11) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5019
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `board_db`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `board_db`.`comment` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `comment` VARCHAR(500) NOT NULL,
  `Date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `boardId` BIGINT(20) NOT NULL,
  `userId` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `comment_boardId_Index` (`boardId` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 10058
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `board_db`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `board_db`.`user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `email` VARCHAR(80) NOT NULL,
  `password` CHAR(41) NOT NULL,
  `enabled` TINYINT(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email` (`email` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 17
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
