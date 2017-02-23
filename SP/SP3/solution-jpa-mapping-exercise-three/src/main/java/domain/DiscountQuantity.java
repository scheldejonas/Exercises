package domain;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by scheldejonas on 23/02/2017.
 */
@Entity
public class DiscountQuantity extends DiscountType {

    @Column(name = "discount_quantity_min_size")
    private int quantityFOrDiscount;

    @Column(name = "discount_quantity")
    double discount;

    public DiscountQuantity() {
        quantityFOrDiscount = 3;
        discount = 0.2;
    }

    @Override
    public double calcDiscount(double pricePerItem, int quantity) {
        if (quantity >= quantityFOrDiscount) {
            return pricePerItem * quantity * discount;
        }
        return 0;
    }
}
