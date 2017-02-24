package service;

import dao.ProjectDao;
import dao.ProjectDaoImpl;
import dao.ProjectUserDao;
import dao.ProjectUserDaoImpl;
import domain.Project;
import domain.ProjectUser;

/**
 * Created by scheldejonas on 24/02/17.
 */
public class ProjectServiceImpl implements ProjectService {

    private static ProjectService singleton = null;
    private ProjectDao projectDao = null;
    private ProjectUserDao projectUserDao = null;

    private ProjectServiceImpl() {
        this.projectDao = ProjectDaoImpl.getSingleton();
        this.projectUserDao = ProjectUserDaoImpl.getSingleton();
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
            projectDao.update(project);
        }
    }
}
