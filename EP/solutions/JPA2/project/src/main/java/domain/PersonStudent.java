package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.UniqueConstraint;
import java.time.LocalDate;

/**
 * Created by scheldejonas on 24/02/2017.
 */
@Entity
public class PersonStudent extends Person {

    @Column(name = "mat_nr", unique = true)
    private int matNr;

    @Column(name = "mat_date")
    private LocalDate matDate;

    public PersonStudent() {
    }

    public PersonStudent(String firstName, String lastName, LocalDate birthDate, int age, boolean married, int matNr, LocalDate matDate) {
        super(firstName, lastName, birthDate, age, married);
        this.matNr = matNr;
        this.matDate = matDate;
    }

    @Override
    public String toString() {
        return "PersonStudent{" +
                "matNr=" + matNr +
                ", matDate=" + matDate +
                "} " + super.toString();
    }

    public int getMatNr() {
        return matNr;
    }

    public void setMatNr(int matNr) {
        this.matNr = matNr;
    }

    public LocalDate getMatDate() {
        return matDate;
    }

    public void setMatDate(LocalDate matDate) {
        this.matDate = matDate;
    }
}
