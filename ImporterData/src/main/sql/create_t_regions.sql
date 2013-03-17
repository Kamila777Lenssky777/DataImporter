CREATE  TABLE `addresses`.`regions` (
  `region_id` BIGINT NOT NULL AUTO_INCREMENT ,
  `region_name` VARCHAR(100) NOT NULL ,
 `region_code` VARCHAR(20) NOT NULL ,
  `country_id` BIGINT NOT NULL ,
  PRIMARY KEY (`region_id`) ,
  INDEX `country_id_idx` (`country_id` ASC) ,
  UNIQUE INDEX `region_id_UNIQUE` (`region_id` ASC) ,
  UNIQUE INDEX `region_name_UNIQUE` (`region_name` ASC) ,
  CONSTRAINT `country_id`
    FOREIGN KEY (`country_id` )
    REFERENCES `addresses`.`countries` (`country_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);