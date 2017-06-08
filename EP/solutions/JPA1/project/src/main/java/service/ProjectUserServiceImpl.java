package service;

import dao.ProjectUserDao;
import dao.ProjectUserDaoImpl;
import domain.ProjectUser;

import java.util.List;

/**
 * Created by scheldejonas on 23/02/2017.
 */
public class ProjectUserServiceImpl implements ProjectUserService {

    private static ProjectUserService singleton = null;
    private ProjectUserDao projectUserDao = ProjectUserDaoImpl.getSingleton();

    private ProjectUserServiceImpl() {
    }

    public static ProjectUserService getSingleton() {
        if (singleton == null) {
            singleton = new ProjectUserServiceImpl();
        }
        return singleton;
    }

    @Override
    public List<ProjectUser> findAll() {
        return projectUserDao.getAllUsers();
    }

    @Override
    public void create(ProjectUser projectUser) {
         projectUserDao.create(projectUser);
    }

    @Override
    public void update(ProjectUser projectUser) {
        projectUserDao.update(projectUser);
    }

    @Override
    public ProjectUser findUser(Long aLong) {
        return projectUserDao.findUser(aLong);
    }

    @Override
    public List<ProjectUser> getAllUsers() {
        return projectUserDao.getAllUsers();
    }
}
