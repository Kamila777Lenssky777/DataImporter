package ua.org.romashchenko.study6.importerdata;

import ua.org.romashchenko.study6.importerdata.importer.DataImporter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.org.romashchenko.study6.importerdata.dao.AddressDao;
import ua.org.romashchenko.study6.importerdata.dao.CityDaoImpl;
import ua.org.romashchenko.study6.importerdata.model.Address;
import ua.org.romashchenko.study6.importerdata.model.City;
import ua.org.romashchenko.study6.importerdata.model.Country;
import ua.org.romashchenko.study6.importerdata.model.Postcode;
import ua.org.romashchenko.study6.importerdata.model.Region;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        String filename = "GB//GB.txt";
        BufferedReader reader = new BufferedReader(new FileReader(filename));


        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring-config.xml");

        DataImporter importer = (DataImporter) context.getBean("dataImporter");
        importer.showCities();
        importer.importData(reader);
        importer.showCities();


        



    }

    public static void showList(List list) {
        System.out.println("List of something");
        for (Object element : list) {
            System.out.println(element);
        }
    }
}
