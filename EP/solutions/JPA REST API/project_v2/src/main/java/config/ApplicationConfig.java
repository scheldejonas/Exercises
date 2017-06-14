package config;

import rest.CountryResource;

import javax.ws.rs.core.Application;
import java.util.Set;

/**
 *
 * @author Thomas Hartmann - tha@cphbusiness.dk created on Mar 6, 2017 
 */
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(rest.CountryResource.class);
    }

}
