package utils;

import entity.Role;
import entity.User;
import facades.UserFacade;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.logging.Level;
import java.util.logging.Logger;

public class makeTestUsers {

  //Only for initial testing REMOVE BEFORE PRODUCTION
  //Run this file to setup the users required to use the initial version of the seed
  public static void main(String[] args) {
    EntityManager em = Persistence.createEntityManagerFactory("pu_development").createEntityManager();
    try {
      System.out.println("Creating TEST Users");
      if (em.find(User.class, "user") == null) {
        em.getTransaction().begin();
        Role userRole = new Role("User");
        Role adminRole = new Role("Admin");
        User user = new User("Peter", "test");
        user.addRole(userRole);
        User admin = new User("Anne", "test");
        admin.addRole(adminRole);
        em.persist(userRole);
        em.persist(adminRole);
        em.persist(user);
        em.persist(admin);
        em.getTransaction().commit();
        System.out.println("Created TEST Users");
      }
    } catch (Exception ex) {
      Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
      em.getTransaction().rollback();
    } finally {
      em.close();
    }
  }
}