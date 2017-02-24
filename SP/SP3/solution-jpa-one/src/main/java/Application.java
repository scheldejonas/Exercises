import domain.ProjectUser;
import service.ProjectUserService;
import service.ProjectUserServiceImpl;

/**
 * Created by scheldejonas on 22/02/2017.
 */
public class Application {
    public static void main(String[] args) {
        try {
            // SP3 -> JPA ONE -> Part two -> Task One
            ProjectUserService projectUserService = ProjectUserServiceImpl.getSingleton();
            projectUserService.create(new ProjectUser());
            // SP3 -> JPA ONE -> Part two -> Task Two
            System.out.println(projectUserService.findUser(new Long(1)));
            // No need to let it stand and rn
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            System.exit(1);
        }
    }
}
