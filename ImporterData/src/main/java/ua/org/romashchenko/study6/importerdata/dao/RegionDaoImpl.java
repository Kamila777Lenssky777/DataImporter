/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.org.romashchenko.study6.importerdata.dao;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.org.romashchenko.study6.importerdata.model.City;
import ua.org.romashchenko.study6.importerdata.model.Country;
import ua.org.romashchenko.study6.importerdata.model.Postcode;
import ua.org.romashchenko.study6.importerdata.model.Region;

/**
 *
 * @author Christina Romashchenko
 */
@Repository
public class RegionDaoImpl implements Dao<Region> {

    @Autowired
    private SimpleJdbcTemplate jdbcTemplate;
    @Autowired
    private CountryDaoImpl countryDao;

    @Override
    public long insert(Region region) {
        if (!isExist(region)) {
            Country country = region.getCountry();
            long countryId = countryDao.insert(country);
            String sql = "INSERT INTO `addresses`.`regions` (`region_name`, `region_code`, `country_id`) VALUES (?,?,?)";
            int numOfRows = jdbcTemplate.update(sql, new Object[]{region.getRegion_name(),
                        region.getRegion_code(),
                        countryId
                    });
        }
        return getId(region);
    }

    @Override
    public boolean multipleInsert(List<Region> objects) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Region> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public long getId(Region object) {
        String sql = "Select region_id from regions  where region_name = ?";
        return jdbcTemplate.queryForLong(sql, object.getRegion_name());
    }

    @Override
    public boolean isExist(Region object) {
        int num = jdbcTemplate.queryForInt("Select count(*) from regions where region_name = ?", object.getRegion_name());
        return num > 0 ? true : false;
    }

    @Override
    public Region getById(long id) {
        String sql = "Select region_name, region_code, country_id from regions  where region_id = ?";
        Map<String, Object> map = jdbcTemplate.queryForMap(sql, id);
        String regionName = "" + map.get("region_name");
        String regionCode = "" + map.get("region_code");
        long countryId = Long.parseLong("" + map.get("country_id"));
        Country country = countryDao.getById(countryId);
        return new Region(regionName, regionCode, country);
    }

    public SimpleJdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(SimpleJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public CountryDaoImpl getCountryDao() {
        return countryDao;
    }

    public void setCountryDao(CountryDaoImpl countryDao) {
        this.countryDao = countryDao;
    }
}
