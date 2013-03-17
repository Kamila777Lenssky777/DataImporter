CREATE  TABLE `addresses`.`postcodes` (
  `postcode_id` BIGINT NOT NULL AUTO_INCREMENT ,
  `postcode` VARCHAR(20) NOT NULL ,
  PRIMARY KEY (`postcode_id`) ,
  UNIQUE INDEX `postcode_id_UNIQUE` (`postcode_id` ASC) ,
  UNIQUE INDEX `postcode_UNIQUE` (`postcode` ASC) );