package service;

import dao.*;
import domain.Project;
import domain.ProjectUser;
import domain.Task;

/**
 * Created by scheldejonas on 24/02/17.
 */
public class ProjectServiceImpl implements ProjectService {

    private static ProjectService singleton = null;
    private ProjectDao projectDao = null;
    private ProjectUserDao projectUserDao = null;
    private TaskDao taskDao = null;

    private ProjectServiceImpl() {
        this.projectDao = ProjectDaoImpl.getSingleton();
        this.projectUserDao = ProjectUserDaoImpl.getSingleton();
        this.taskDao = TaskDaoImpl.getSingleton();
    }

    public static ProjectService getSingleton() {
        if (singleton == null) {
            singleton = new ProjectServiceImpl();
        }
        return singleton;
    }

    @Override
    public void createProject(Project project) {
        projectDao.createProject(project);
    }

    @Override
    public void assignUserToProject(Long userId, Long projectId) {
        if (userId == null) {
            throw new RuntimeException("You are missing to give in the user id, when tried to assign user to project");
        }
        if (projectId == null) {
            throw new RuntimeException("You are missing to give in the project id, when tried to assign user to projet");
        }
        if (projectId != null && userId != null) {
            Project project = projectDao.findProject(projectId);
            ProjectUser projectUser = projectUserDao.findUser(userId);
            project.getProjectUserList().add(projectUser);
            projectUser.getProjectList().add(project);
            projectDao.update(project);
            projectUserDao.update(projectUser);
        }
    }

    @Override
    public void createTaskAndAssignToProject(String taskDescription, Long projectId) {
        try {
            if (taskDescription == null || taskDescription.equals("")) {
                throw new RuntimeException("You are missing the task description, when tried to create task and assign to project");
            }
            if (projectId == null) {
                throw new RuntimeException("You are missing the project id, when tried to create task and assign to project");
            }

            Task task = new Task("No name yet", taskDescription,10, 0);
            taskDao.createTask(task);

            Project project = projectDao.findProject(projectId);

            task.setProject(project);
            taskDao.updateTask(task);

            project.getTaskList().add(task);
            projectDao.update(project);

//            Project projectTwo = projectDao.findProject(projectId);
//            System.out.println("...project" + project.toString());

        } catch (RuntimeException exception) {
            System.out.println(exception.getMessage());
        }

    }
}
