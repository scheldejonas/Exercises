package domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by scheldejonas on 22/02/2017.
 */
@Entity
@Table(name = "Grade")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "value")
    private int value;

    @OneToMany(mappedBy = "grade")
    private List<Person> personList = new ArrayList<>();

    public Grade() {
    }

    public Grade(String name, int value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
}
