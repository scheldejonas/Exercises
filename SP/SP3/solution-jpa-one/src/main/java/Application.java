import domain.Project;
import domain.ProjectUser;
import service.ProjectService;
import service.ProjectServiceImpl;
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
            // SP3 -> JPA ONE -> Part two -> Task Three
            projectUserService.create(new ProjectUser());
            projectUserService.create(new ProjectUser());
            projectUserService.create(new ProjectUser());
            for (ProjectUser projectUser : projectUserService.getAllUsers()) {
                System.out.println(projectUser);
            }
            // SP3 -> JPA ONE -> Part two -> Task Four
            ProjectService projectService = ProjectServiceImpl.getSingleton();
            projectService.createProject(new Project());
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            // No need to let it stand and run
            System.exit(1);
        }
    }
}
