package domain;

import javax.persistence.*;

/**
 * Created by scheldejonas on 22/02/2017.
 */
@Entity
@Table(name = "Customer")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price")
    private double price;

    @Column(name = "first_name")
    private String firstName;

    @ManyToOne(cascade = CascadeType.ALL)
    private DiscountType discountType;

    public Customer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return this.discountType.calcDiscount(20,5);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", price=" + price +
                ", firstName='" + firstName + '\'' +
                ", discountType id=" + ((discountType == null) ? "null" : discountType.getId()) +
                '}';
    }
}
