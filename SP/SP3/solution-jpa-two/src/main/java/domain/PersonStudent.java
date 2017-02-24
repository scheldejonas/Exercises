package domain;

import javax.persistence.Column;
import java.time.LocalDate;

/**
 * Created by scheldejonas on 24/02/2017.
 */
public class PersonStudent extends Person {

    @Column(name = "mat_nr")
    private int matNr;

    @Column(name = "mat_date")
    private LocalDate matDate;

    public PersonStudent() {
    }

    @Override
    public String toString() {
        return "PersonStudent{" +
                "matNr=" + matNr +
                ", matDate=" + matDate +
                '}';
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
