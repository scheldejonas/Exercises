package service;

import dao.ProjectDao;
import dao.ProjectDaoImpl;
import domain.Project;

/**
 * Created by scheldejonas on 24/02/17.
 */
public class ProjectServiceImpl implements ProjectService {

    private static ProjectService singleton = null;
    private ProjectDao projectDao = null;

    private ProjectServiceImpl() {
        this.projectDao = ProjectDaoImpl.getSingleton();
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
}
