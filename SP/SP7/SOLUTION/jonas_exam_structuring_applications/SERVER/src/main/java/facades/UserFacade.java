package facades;

import entity.User;
import security.IUser;
import security.IUserFacade;
import security.PasswordStorage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class UserFacade implements IUserFacade {

    EntityManagerFactory emf;

    public UserFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public IUser getUserByUserId(String id) {
        EntityManager em = getEntityManager();
        try {
            System.out.println("...Trying to get the user from db with id: " + id);
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    /*
    Return the Roles if users could be authenticated, otherwise null
     */
    @Override
    public List<String> authenticateUser(String userName, String password) throws PasswordStorage.InvalidHashException, PasswordStorage.CannotPerformOperationException {
        IUser user = getUserByUserId(userName);
        return user != null && PasswordStorage.verifyPassword(password, user.getPassword()) ? user.getRolesAsStrings() : null;
    }

}