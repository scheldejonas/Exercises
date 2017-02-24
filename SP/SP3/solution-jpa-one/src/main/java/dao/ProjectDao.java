package dao;

import domain.Project;

/**
 * Created by scheldejonas on 24/02/17.
 */
public interface ProjectDao {
    void createProject(Project project);

    Project findProject(Long projectId);

    void update(Project project);
}
