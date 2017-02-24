package domain;

import org.hibernate.annotations.CollectionId;

import javax.persistence.*;

/**
 * Created by scheldejonas on 23/02/2017.
 */
@Entity
public class DiscountFixed extends DiscountType {

    @Column(name = "discount_fixed")
    protected double discount;

    public DiscountFixed() {
        discount = 0.1;
    }

    @Override
    public double calcDiscount(double pricePerItem, int quantity) {
        return pricePerItem * discount * quantity;
    }

}
