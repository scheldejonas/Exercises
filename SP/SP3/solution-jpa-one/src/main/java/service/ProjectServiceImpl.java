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
            Project project = (Project) projectDao.findProject(projectId);
            ProjectUser projectUser = (ProjectUser) projectUserDao.findUser(userId);
            project.getProjectUserList().add(projectUser);
            projectDao.update(project);
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
            Task task = new Task();
            task.setDescription(taskDescription);
            taskDao.createTask(task);
            Project project = (Project) projectDao.findProject(projectId);
            project.getTaskList().add(task);
            projectDao.update(project);
        } catch (RuntimeException exception) {
            System.out.println(exception.getMessage());
        }

    }
}
