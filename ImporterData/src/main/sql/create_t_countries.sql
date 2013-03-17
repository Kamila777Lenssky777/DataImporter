CREATE  TABLE `addresses`.`countries` (
  `country_id` BIGINT NOT NULL AUTO_INCREMENT ,
  `country_name` VARCHAR(100) NOT NULL ,
  `country_code` VARCHAR(20) NOT NULL ,
  PRIMARY KEY (`country_id`) ,
  UNIQUE INDEX `country_id_UNIQUE` (`country_id` ASC) ,
  UNIQUE INDEX `country_name_UNIQUE` (`country_name` ASC) );