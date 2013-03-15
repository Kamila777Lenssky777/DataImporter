/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.org.romashchenko.study6.importerdata.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.org.romashchenko.study6.importerdata.model.Address;
import ua.org.romashchenko.study6.importerdata.model.City;
import ua.org.romashchenko.study6.importerdata.model.Country;
import ua.org.romashchenko.study6.importerdata.model.Postcode;
import ua.org.romashchenko.study6.importerdata.model.Region;

/**
 *
 * @author Christina Romashchenko
 */
@Repository
public class CityDaoImpl implements Dao<City> {

    @Autowired
    private SimpleJdbcTemplate jdbcTemplate;
    @Autowired
    private RegionDaoImpl regionDao;
    @Autowired
    private PostcodeDaoImpl postcodeDao;

    @Override
    public long insert(City city) {
        if (!isExist(city)) {
            Region region = city.getRegion();
            Postcode postcode = city.getPostcode();
            long regionId = regionDao.insert(region);
            long postcodeId = postcodeDao.insert(postcode);
            String sql = "INSERT INTO `addresses`.`cities` (`city_name`, `region_id`, `postcode_id`) VALUES (?,?,?)";
            int numOfRows = jdbcTemplate.update(sql, new Object[]{city.getCity_name(),
                        regionId,
                        postcodeId
                    });
        }
        return getId(city);
    }

    @Override
    public boolean multipleInsert(List<City> objects) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<City> findAll() {

        String sql = "Select city_id from cities g";
        List<Map<String, Object>> ides = jdbcTemplate.queryForList(sql);
        List<City> result = new ArrayList<City>(ides.size());
        for (int i = 0; i < ides.size(); i++) {
            City city = getById(Long.parseLong("" + ides.get(i).get("city_id")));
            result.add(city);
        }
        return result;
    }

    @Override
    public long getId(City city) {
        String sql = "Select city_id from cities  where city_name = ?";
        return jdbcTemplate.queryForLong(sql, city.getCity_name());
    }

    @Override
    public boolean isExist(City city) {
        int num = jdbcTemplate.queryForInt("Select count(*) from cities where city_name = ?", city.getCity_name());
        return num > 0 ? true : false;
    }

    @Override
    public City getById(long id) {
        String sql = "Select city_name,region_id, postcode_id from cities  where city_id = ?";
        Map<String, Object> map = jdbcTemplate.queryForMap(sql, id);
        String cityName = "" + map.get("city_name");
        long regionId = Long.parseLong("" + map.get("region_id"));
        long postcodeId = Long.parseLong("" + map.get("postcode_id"));
        Region region = regionDao.getById(regionId);
        Postcode code = postcodeDao.getById(postcodeId);
        return new City(cityName, region, code);
    }

    public SimpleJdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(SimpleJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public RegionDaoImpl getRegionDao() {
        return regionDao;
    }

    public void setRegionDao(RegionDaoImpl regionDao) {
        this.regionDao = regionDao;
    }

    public PostcodeDaoImpl getPostcodeDao() {
        return postcodeDao;
    }

    public void setPostcodeDao(PostcodeDaoImpl postcodeDao) {
        this.postcodeDao = postcodeDao;
    }
}
