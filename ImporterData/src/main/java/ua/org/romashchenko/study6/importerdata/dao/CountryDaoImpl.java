/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.org.romashchenko.study6.importerdata.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
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
public class CountryDaoImpl implements Dao<Country> {

    @Autowired
    private SimpleJdbcTemplate jdbcTemplate;

    @Override
    public long insert(Country object) {
        if (!isExist(object)) {
            jdbcTemplate.update("INSERT INTO `addresses`.`countries` (`country_name`, `country_code`) VALUES (?, ?)", object.getCountry_name(), object.getCountry_code());

        };
        return getId(object);
    }

    @Override
    public boolean multipleInsert(List<Country> objects) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Country> findAll() {
        RowMapper<Country> mapper = new RowMapper() {
            @Override
            public Country mapRow(ResultSet rs, int num) throws SQLException {
                return new Country(rs.getString("country_name"), rs.getString("country_code"));
            }
        };
        return jdbcTemplate.query(
                "select country_name, country_code "
                + "from countries g ",
                mapper);
    }

    @Override
    public long getId(Country object) {
        String sql = "Select country_id from countries  where country_name = ?";
        return jdbcTemplate.queryForLong(sql, object.getCountry_name());
    }

    @Override
    public boolean isExist(Country object) {
        int num = jdbcTemplate.queryForInt("Select count(*) from countries where country_name = ?", object.getCountry_name());
        return num > 0 ? true : false;
    }

    @Override
    public Country getById(long id) {
        String sql = "Select country_name, country_code from countries  where country_id = ?";
        Map<String, Object> map = jdbcTemplate.queryForMap(sql, id);
        String countryName = "" + map.get("country_name");
        String countryCode = "" + map.get("country_code");
        return new Country(countryName, countryCode);
    }
}
