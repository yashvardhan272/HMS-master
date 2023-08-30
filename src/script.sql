-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema ezstay
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ezstay
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ezstay` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `ezstay` ;

-- -----------------------------------------------------
-- Table `ezstay`.`bookings`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ezstay`.`bookings` (
  `booking_id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NOT NULL,
  `check_in` DATE NOT NULL,
  `check_out` DATE NULL DEFAULT NULL,
  `room_no` INT NOT NULL,
  `stay_period` INT NULL DEFAULT NULL,
  `total_amount` DOUBLE NULL DEFAULT NULL,
  `price` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`booking_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ezstay`.`customers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ezstay`.`customers` (
  `customer_id` INT NOT NULL AUTO_INCREMENT,
  `customer_name` VARCHAR(45) NOT NULL,
  `mobile_number` BIGINT UNSIGNED NOT NULL,
  `nationality` VARCHAR(45) NOT NULL,
  `gender` VARCHAR(45) NOT NULL,
  `email` VARCHAR(90) NOT NULL,
  `id_proof` VARCHAR(45) NOT NULL,
  `id_number` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `mobile_number_UNIQUE` (`mobile_number` ASC) VISIBLE,
  UNIQUE INDEX `id_number_UNIQUE` (`id_number` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ezstay`.`employees`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ezstay`.`employees` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `dob` DATE NOT NULL,
  `designation` VARCHAR(45) NOT NULL,
  `gender` VARCHAR(15) NOT NULL,
  `salary` DOUBLE NOT NULL,
  `mobile` BIGINT UNSIGNED NOT NULL,
  `aadhaar` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `mobile_UNIQUE` (`mobile` ASC) VISIBLE,
  UNIQUE INDEX `aadhaar_UNIQUE` (`aadhaar` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ezstay`.`rooms`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ezstay`.`rooms` (
  `room_no` INT UNSIGNED NOT NULL,
  `room_type` VARCHAR(45) NOT NULL,
  `sharing` VARCHAR(45) NOT NULL,
  `price` DOUBLE NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`room_no`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ezstay`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ezstay`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
