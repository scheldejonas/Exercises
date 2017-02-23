package domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by scheldejonas on 23/02/2017.
 */
@Entity
@Table(name = "DiscountType")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class DiscountType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "discountType")
    private List<Customer> customerList;

    public DiscountType() {
        customerList = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public double calcDiscount(int pricePerItem, int quantity) {
        return pricePerItem*quantity;
    }
}
