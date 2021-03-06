package dao;

import domain.ProjectUser;

import java.util.List;

/**
 * Created by scheldejonas on 22/02/2017.
 */
public interface ProjectUserDao {
    void create(ProjectUser projectUser);

    ProjectUser findUser(Long id);

    List<ProjectUser> getAllUsers();

    void update(ProjectUser projectUser);
}
