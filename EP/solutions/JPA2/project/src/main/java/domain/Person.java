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
@Table(name = "Person")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "age")
    private int age;

    @Column(name = "married")
    private boolean married;

    @ManyToOne
    private Grade grade;

    @OneToMany(mappedBy = "supervisor")
    private List<Person> supervised = new ArrayList<>();

    @ManyToOne
    private Person supervisor;

    public Person() {
    }

    public Person(String firstName, String lastName, LocalDate birthDate, int age, boolean married) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.age = age;
        this.married = married;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", age=" + age +
                ", married=" + married +
                '}';
    }

    public List<Person> getSupervised() {
        return supervised;
    }

    public void setSupervised(List<Person> supervised) {
        this.supervised = supervised;
    }

    public Person getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Person supervisor) {
        this.supervisor = supervisor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

}
