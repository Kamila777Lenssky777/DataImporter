CREATE  TABLE `addresses`.`cities` (
  `city_id` BIGINT NOT NULL AUTO_INCREMENT ,
  `city_name` VARCHAR(225) NOT NULL ,
  `region_id` BIGINT NOT NULL ,
  `postcode_id` BIGINT NOT NULL ,
  PRIMARY KEY (`city_id`) ,
  UNIQUE INDEX `city_id_UNIQUE` (`city_id` ASC) ,
  UNIQUE INDEX `city_name_UNIQUE` (`city_name` ASC) ,
  INDEX `region_id_idx` (`region_id` ASC) ,
  INDEX `postcode_id_idx` (`postcode_id` ASC) ,
  CONSTRAINT `region_id`
    FOREIGN KEY (`region_id` )
    REFERENCES `addresses`.`regions` (`region_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `postcode_id`
    FOREIGN KEY (`postcode_id` )
    REFERENCES `addresses`.`postcodes` (`postcode_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);