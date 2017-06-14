/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import com.domain.restapi.domain.Country;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author schelde
 */
public class CountryFacade {
    private static final CountryFacade singleton = new CountryFacade();
    public EntityManager entityManager = Persistence.createEntityManagerFactory("com.domain_restapi_jar_0.1PU").createEntityManager();

    private CountryFacade() {
    }
    
    public static CountryFacade getSingleton() {
        return singleton;
    }
    
    /**
     *
     * @return 
     */
    public List<Country> findAll() {
        List<Country> countryList = (List<Country>) entityManager.createQuery("select c from Country c");
        return countryList;
    }
    
}
