package service;

import dao.DiscountTypeDao;
import dao.DiscountTypeDaoImpl;
import domain.DiscountType;

/**
 * Created by scheldejonas on 23/02/2017.
 */
public class DiscountTypeServiceImpl implements DiscountTypeService {

    private static DiscountTypeService singleton = null;
    private DiscountTypeDao discountTypeDao = DiscountTypeDaoImpl.getSingleton();

    private DiscountTypeServiceImpl() {
    }

    public static DiscountTypeService getSingleton() {
        if (singleton == null) {
            singleton = new DiscountTypeServiceImpl();
        }
        return singleton;
    }

    @Override
    public void save(DiscountType discountType) {
        discountTypeDao.save(discountType);
    }
}
