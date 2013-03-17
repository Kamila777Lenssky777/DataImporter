CREATE  TABLE `addresses`.`addresses` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `country` VARCHAR(45) NULL ,
  `region` VARCHAR(45) NULL ,
  `city` VARCHAR(45) NULL ,
  `postcode` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) );