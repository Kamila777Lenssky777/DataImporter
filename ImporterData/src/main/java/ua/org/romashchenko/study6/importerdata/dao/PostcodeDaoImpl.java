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
import ua.org.romashchenko.study6.importerdata.model.Country;
import ua.org.romashchenko.study6.importerdata.model.Postcode;

/**
 *
 * @author Christina Romashchenko
 */
@Repository
public class PostcodeDaoImpl implements Dao<Postcode> {

    @Autowired
    private SimpleJdbcTemplate jdbcTemplate;

    @Override
    public long insert(Postcode object) {
        if (!isExist(object)) {
            jdbcTemplate.update("INSERT INTO `addresses`.`postcodes` (`postcode`) VALUES (?)", object.getPostcode());

        };
        return getId(object);
    }

    @Override
    public boolean multipleInsert(List<Postcode> objects) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Postcode> findAll() {
        RowMapper<Postcode> mapper = new RowMapper() {
            @Override
            public Postcode mapRow(ResultSet rs, int num) throws SQLException {
                return new Postcode(rs.getString("postcode"));
            }
        };
        return jdbcTemplate.query(
                "select postcode "
                + "from postcodes g ",
                mapper);
    }

    @Override
    public long getId(Postcode object) {
        String sql = "Select postcode_id from postcodes  where postcode = ?";
        return jdbcTemplate.queryForLong(sql, object.getPostcode());
    }

    @Override
    public boolean isExist(Postcode object) {
        int num = jdbcTemplate.queryForInt("Select count(*) from postcodes where postcode = ?", object.getPostcode());
        return num > 0 ? true : false;
    }

    @Override
    public Postcode getById(long id) {
        String sql = "Select postcode from postcodes  where postcode_id = ?";
        Map<String, Object> map = jdbcTemplate.queryForMap(sql, id);
        String postcode = "" + map.get("postcode");
        return new Postcode(postcode);
    }
}
