package dao;

import domain.ProjectUser;

import java.util.List;

/**
 * Created by scheldejonas on 22/02/2017.
 */
public interface ProjectUserDao {
    void save(ProjectUser projectUser);

    List<ProjectUser> findAll();

    void update(ProjectUser projectUser);
}
