package ua.org.romashchenko.study6.importerdata;

import java.util.List;
import java.util.Random;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.org.romashchenko.study6.importerdata.dao.AddressDao;
import ua.org.romashchenko.study6.importerdata.model.Address;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring-config.xml");

        AddressDao customerDAO = (AddressDao) context.getBean("addressDaoImpl");
        System.out.println("Table addresses : ");
        showAddresses(customerDAO);
        System.out.println("Filling of table, so table: ");
        for (int i = 0; i < 2; i++) {
            int num = new Random(1000).nextInt();
            customerDAO.insert(new Address("Ukraine" + num, "Kiev", "Kiev", "post" + num));
        }
        showAddresses(customerDAO);
    }

    public static void showAddresses(AddressDao dao) {
        List<Address> list = dao.findAllAddresses();
        System.out.println("listSize = " + list.size());
        for (Address address : list) {
            System.out.println(address);
        }
        System.out.println("-----------------------");
    }
}
