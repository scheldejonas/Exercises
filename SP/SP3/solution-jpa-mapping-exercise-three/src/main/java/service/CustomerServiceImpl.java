package service;

import dao.CustomerDao;
import dao.CustomerDaoImpl;
import domain.Customer;
import domain.DiscountFixed;
import domain.DiscountType;

import java.util.List;

/**
 * Created by scheldejonas on 23/02/2017.
 */
public class CustomerServiceImpl implements CustomerService {

    private static CustomerService singleton = null;
    private CustomerDao customerDao = CustomerDaoImpl.getSingleton();
    private DiscountTypeService discountTypeService = DiscountTypeServiceImpl.getSingleton();

    private CustomerServiceImpl() {
    }

    public static CustomerService getSingleton() {
        if (singleton == null) {
            singleton = new CustomerServiceImpl();
        }
        return singleton;
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    public void save(Customer customer) {
        if (customer.getDiscountType() == null || customer.getDiscountType().getId() == null) {
            System.out.println("I was here");
            DiscountType discountType = new DiscountFixed();
            discountType.getCustomerList().add(customer);
            discountTypeService.save(discountType);
            System.out.println(discountType.toString());
            customer.setDiscountType(discountType);
        }
         customerDao.save(customer);
    }

    @Override
    public void update(Customer customer) {
        if (customer.getDiscountType() == null || customer.getDiscountType().getId() == null) {

        }
        customerDao.update(customer);
    }
}
