import service.ProjectUserService;
import service.ProjectUserServiceImpl;

/**
 * Created by scheldejonas on 22/02/2017.
 */
public class Application {
    public static void main(String[] args) {
        // SP3 -> Task One -> Part Four
        ProjectUserService projectUserService = ProjectUserServiceImpl.getSingleton();

        // No need to let it stand and rn
        System.exit(1);
    }
}
