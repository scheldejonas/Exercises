import domain.Project;
import domain.ProjectUser;
import service.ProjectService;
import service.ProjectServiceImpl;
import service.ProjectUserService;
import service.ProjectUserServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by scheldejonas on 22/02/2017.
 */
public class Application {
    public static void main(String[] args) {
        try {

            // SP3 -> JPA ONE -> Part two -> Task One
            ProjectUserService projectUserService = ProjectUserServiceImpl.getSingleton();
            projectUserService.create(new ProjectUser("user0","mail1@domain.com", LocalDateTime.now()));

            // SP3 -> JPA ONE -> Part two -> Task Two
            System.out.println(projectUserService.findUser(new Long(1)));

            // SP3 -> JPA ONE -> Part two -> Task Three
            projectUserService.create(new ProjectUser("user1","mail1@domain.com", LocalDateTime.now()));
            projectUserService.create(new ProjectUser("user2","mail2@domain.com", LocalDateTime.now()));
            projectUserService.create(new ProjectUser("user3","mail3@domain.com", LocalDateTime.now()));
            for (ProjectUser projectUser : projectUserService.getAllUsers()) {
                System.out.println(projectUser);
            }

            // SP3 -> JPA ONE -> Part two -> Task Four
            ProjectService projectService = ProjectServiceImpl.getSingleton();
            Project project = new Project("Prepare for Exam", "To prepare code and understanding of learning objectives", LocalDateTime.now(), LocalDateTime.now());
            projectService.createProject(project);

            // JPA ONE -> Part two -> Task Six
            projectService.assignUserToProject(new Long(1), new Long(1));

            // SP3 -> JPA ONE -> Part two -> Task Seven
            projectService.createTaskAndAssignToProject("Make the rest of the methods for CRUD operations on Task",new Long(1));
            projectService.createTaskAndAssignToProject("This is a funny test example of a task",new Long(1));

        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            // No need to let it stand and run
            System.exit(1);
        }
    }
}
