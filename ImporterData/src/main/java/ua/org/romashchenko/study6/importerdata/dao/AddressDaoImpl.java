package ua.org.romashchenko.study6.importerdata.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.tree.TreePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.org.romashchenko.study6.importerdata.model.Address;

/**
 * @author Christina Romashchenko
 */
@Repository
public class AddressDaoImpl implements AddressDao {

    @Autowired
    private SimpleJdbcTemplate jdbcTemplate;

    public boolean insert(Address address) {
        String sql = "insert into `addresses` (`country`, `region`, `city`, `postcode`) values (?,?,?,?)";

        int numOfRows = jdbcTemplate.update(sql, new Object[]{address.getCountry(),
                    address.getRegion(),
                    address.getCity(),
                    address.getPostcode()
                });
        return numOfRows != 0;

    }

    public List<Address> findAllAddresses() {
        RowMapper<Address> mapper = new RowMapper() {
            public Address mapRow(ResultSet rs, int num) throws SQLException {
                return new Address(rs.getLong("id"), rs.getString("country"), rs.getString("region"), rs.getString("city"), rs.getString("postcode"));
            }
        };
        return jdbcTemplate.query(
                "select id, country, region, city, postcode "
                + "from addresses g "
                + "group by id, country",
                mapper);
    }

    @Override
    public String toString() {
        return "AddressDaoImpl{" + "jdbcTemplate=" + jdbcTemplate + '}';
    }
}
