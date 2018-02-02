-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema nhn_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema nhn_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `nhn_db` DEFAULT CHARACTER SET utf8 ;
USE `nhn_db` ;

-- -----------------------------------------------------
-- Table `nhn_db`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nhn_db`.`employee` (
  `empId` INT(11) NOT NULL AUTO_INCREMENT,
  `employeeName` VARCHAR(50) NOT NULL,
  `birth` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`empId`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `nhn_db`.`student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nhn_db`.`student` (
  `stuId` INT(11) NOT NULL AUTO_INCREMENT,
  `studentName` VARCHAR(50) NOT NULL,
  `birth` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`stuId`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `nhn_db`.`subject`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nhn_db`.`subject` (
  `subId` INT(11) NOT NULL AUTO_INCREMENT,
  `subjectName` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`subId`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `nhn_db`.`grade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nhn_db`.`grade` (
  `stuId` INT(11) NOT NULL,
  `subId` INT(11) NOT NULL,
  `score` INT(11) NULL DEFAULT '0',
  PRIMARY KEY (`stuId`, `subId`),
  INDEX `subId` (`subId` ASC),
  CONSTRAINT `grade_ibfk_1`
    FOREIGN KEY (`stuId`)
    REFERENCES `nhn_db`.`student` (`stuId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `grade_ibfk_2`
    FOREIGN KEY (`subId`)
    REFERENCES `nhn_db`.`subject` (`subId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `nhn_db`.`teacher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nhn_db`.`teacher` (
  `teacherId` INT(11) NOT NULL AUTO_INCREMENT,
  `teacherName` VARCHAR(50) NOT NULL,
  `birth` VARCHAR(50) NOT NULL,
  `subId` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`teacherId`),
  INDEX `subId` (`subId` ASC),
  CONSTRAINT `teacher_ibfk_1`
    FOREIGN KEY (`subId`)
    REFERENCES `nhn_db`.`subject` (`subId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
