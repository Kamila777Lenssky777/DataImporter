/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.org.romashchenko.study6.importerdata.importer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ua.org.romashchenko.study6.importerdata.dao.AddressDao;
import ua.org.romashchenko.study6.importerdata.model.Address;

/**
 *
 * @author Romashchenko Christina
 */
@Repository
public class DataImporter {

    @Autowired
    private AddressDao dao;

    public void importData(BufferedReader reader) throws IOException {
        String line;
        List<Address> addresses = new ArrayList<Address>(28000);
        List<String> words = new LinkedList<String>();
        while ((line = reader.readLine()) != null) {
            words.addAll(Arrays.asList(line.split("\\t")));
            ListIterator<String> iterator = words.listIterator();
            while (iterator.hasNext() && words.size() >= 12) {
                String country = iterator.next();   //1 
                iterator.remove();
                iterator.next();                    //2
                iterator.remove();
                String city = iterator.next();      //3
                iterator.remove();
                String region = iterator.next();    //4
                iterator.remove();
                String postCode = iterator.next();   //5
                iterator.remove();
                iterator.next();    //6
                iterator.remove();
                for (int i = 0; i < 6; i++) {
                    iterator.next();
                    iterator.remove();
                }
                addresses.add(new Address(country, region, city, postCode));
            }
        }
        dao.multipleInsert(addresses);
    }

    public void showAddresses() {
        System.out.println("Addresses:");
        for (Address address : dao.findAllAddresses()) {
            System.out.println(address);
        }
        System.out.println("-----------------------");
    }

    public void setDao(AddressDao dao) {
        this.dao = dao;
    }

    public AddressDao getDao() {
        return dao;
    }
}
