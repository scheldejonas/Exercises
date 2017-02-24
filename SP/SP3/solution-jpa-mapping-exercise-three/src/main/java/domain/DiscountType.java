package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by scheldejonas on 23/02/2017.
 */
@Entity
@Table(name = "DiscountType")
@Inheritance(strategy = InheritanceType.JOINED)
public class DiscountType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "discountType", cascade = CascadeType.ALL)
    private List<Customer> customerList;

    public DiscountType() {
        customerList = new ArrayList<>();
        id = null;
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

    public double calcDiscount(double pricePerItem, int quantity) {
        return pricePerItem*quantity;
    }

    @Override
    public String toString() {
        return "DiscountType{" +
                "id=" + id +
                ", customerList=" + customerList +
                '}';
    }
}
