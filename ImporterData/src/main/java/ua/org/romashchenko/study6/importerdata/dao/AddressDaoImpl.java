package ua.org.romashchenko.study6.importerdata.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.tree.TreePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.org.romashchenko.study6.importerdata.model.Address;

/**
 * @author Christina Romashchenko
 */
@Repository
public class AddressDaoImpl implements AddressDao {

    @Autowired
    private SimpleJdbcTemplate jdbcTemplate;

    @Override
    public boolean insert(Address address) {
        String sql = "insert into `addresses` (`country`, `region`, `city`, `postcode`) values (?,?,?,?)";

        int numOfRows = jdbcTemplate.update(sql, new Object[]{address.getCountry(),
                    address.getRegion(),
                    address.getCity(),
                    address.getPostcode()
                });
        return numOfRows != 0;


    }

    @Override
    public List<Address> findAllAddresses() {
        RowMapper<Address> mapper = new RowMapper() {
            @Override
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

    @Override
    @Transactional
    public boolean  multipleInsert(final List<Address> addresses) {
        String sql = "insert into `addresses` (`country`, `region`, `city`, `postcode`) values (?,?,?,?)";
       

        //PreparedStatement ps = jdbcTemplate.
//       jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
// 
//	@Override
//	public void setValues(PreparedStatement ps, int i) throws SQLException {
//		Address  address= addresses.get(i);
//		ps.setString(1, address.getCountry());
//		ps.setString(2, address.getRegion());
//		ps.setString(3, address.getCity() );
//                ps.setString(4, address.getPostcode());
//	}
// 
//	@Override
//	public int getBatchSize() {
//		return addresses.size();
//	}
//  });
        for(int i=0; i<addresses.size(); i++){
            Address address = addresses.get(i);
            jdbcTemplate.update(sql, new Object[]{address.getCountry(),
                    address.getRegion(),
                    address.getCity(),
                    address.getPostcode()
                });
        }
        return true;
    }
}
