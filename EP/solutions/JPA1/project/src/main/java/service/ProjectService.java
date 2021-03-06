package service;

import domain.Project;

/**
 * Created by scheldejonas on 24/02/17.
 */
public interface ProjectService {

    void createProject(Project project);

    void assignUserToProject(Long userId, Long projectId);

    void createTaskAndAssignToProject(String taskDescription, Long projectId);

}
