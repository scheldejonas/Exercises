package domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by scheldejonas on 24/02/17.
 */
@Entity
@Table(name = "Project")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "lastModified")
    private LocalDateTime lastModified;

    @ManyToMany(mappedBy = "projectList")
    private List<ProjectUser> projectUserList = new ArrayList<>();

    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    private List<Task> taskList = new ArrayList<>();

    public Project() {
    }

    public Project(String name, String description, LocalDateTime created, LocalDateTime lastModified) {
        this.name = name;
        this.description = description;
        this.created = created;
        this.lastModified = lastModified;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", created=" + created +
                ", lastModified=" + lastModified +
                ", projectUserList=" + projectUserList +
                ", taskList=" + taskList +
                '}';
    }

    public List<ProjectUser> getProjectUserList() {
        return projectUserList;
    }

    public void setProjectUserList(List<ProjectUser> projectUserList) {
        this.projectUserList = projectUserList;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}
