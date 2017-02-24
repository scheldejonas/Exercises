package service;

import domain.ProjectUser;

import java.util.List;

/**
 * Created by scheldejonas on 23/02/2017.
 */
public interface ProjectUserService {
    List<ProjectUser> findAll();

    void create(ProjectUser projectUser);

    void update(ProjectUser projectUser);

    ProjectUser findUser(Long aLong);
}
