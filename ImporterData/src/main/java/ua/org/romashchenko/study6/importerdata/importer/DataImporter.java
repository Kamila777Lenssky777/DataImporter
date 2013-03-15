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
import ua.org.romashchenko.study6.importerdata.dao.CityDaoImpl;
import ua.org.romashchenko.study6.importerdata.model.Address;
import ua.org.romashchenko.study6.importerdata.model.City;
import ua.org.romashchenko.study6.importerdata.model.Country;
import ua.org.romashchenko.study6.importerdata.model.Postcode;
import ua.org.romashchenko.study6.importerdata.model.Region;

/**
 *
 * @author Romashchenko Christina
 */
@Repository
public class DataImporter {

    @Autowired
    private CityDaoImpl dao;

    public void importData(BufferedReader reader) throws IOException {
        String line;
        List<City> cities = new ArrayList<City>(28000);
        List<String> words = new LinkedList<String>();
        while ((line = reader.readLine()) != null) {
            words.addAll(Arrays.asList(line.split("\\t")));
            ListIterator<String> iterator = words.listIterator();
            while (iterator.hasNext() && words.size() >= 12) {
                iterator.next();   //1 
                iterator.remove();
                String postcode = iterator.next();                    //2
                iterator.remove();
                String cityName = iterator.next();      //3
                iterator.remove();
                String countryname = iterator.next();    //4
                iterator.remove();
                String countryCode = iterator.next();   //5
                iterator.remove();
                String regionName = iterator.next();    //6
                iterator.remove();
                String regionCode = iterator.next();    //6
                iterator.remove();
                for (int i = 0; i < 5; i++) {
                    iterator.next();
                    iterator.remove();
                }
                Country country = new Country(countryname, countryCode);
                Region region = new Region(regionName, regionCode, country);
                Postcode code = new Postcode(postcode);
                City city = new City(cityName, region, code);
                cities.add(city);
                dao.insert(city);
            }
        }
        //dao.multipleInsert(addresses);
    }

    public void showCities() {
        System.out.println("Cities:");
        for (City city : dao.findAll()) {
            System.out.println(city);
        }
        System.out.println("-----------------------");
    }

    public CityDaoImpl getDao() {
        return dao;
    }

    public void setDao(CityDaoImpl dao) {
        this.dao = dao;
    }
}
