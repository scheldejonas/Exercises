import config.DataConfig;
import dao.CustomerDao;
import dao.CustomerDaoImpl;
import domain.Customer;
import domain.DiscountFixed;
import service.CustomerService;
import service.CustomerServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by scheldejonas on 22/02/2017.
 */
public class Application {
    public static void main(String[] args) {
        // SP3 -> Task One -> Part Four
        CustomerService customerService = CustomerServiceImpl.getSingleton();
        Customer customer = new Customer();
        customer.setFirstName("Jonas");
        customerService.save(customer);
        System.out.println(customer.toString());
        customer.setDiscountType(new DiscountFixed());
        customerService.update(customer);

        // No need to let it stand and rn
        System.exit(1);
    }
}
