package ua.org.romashchenko.study6.importerdata.dao;

import java.util.List;
import ua.org.romashchenko.study6.importerdata.model.Address;

public interface AddressDao {

    public boolean insert(Address address);

    public List<Address> findAllAddresses();
}
