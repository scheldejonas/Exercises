package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * Created by scheldejonas on 24/02/2017.
 */
@Entity
public class PersonEmployee extends Person {

    @Column(name = "so_sec_nr")
    private int soSecNr;

    @Column(name = "wage")
    private float wage;

    @Column(name = "tax_class")
    private String taxClass;

    public PersonEmployee() {
    }

    public PersonEmployee(String firstName, String lastName, LocalDate birthDate, int age, boolean married, int soSecNr, float wage, String taxClass) {
        super(firstName, lastName, birthDate, age, married);
        this.soSecNr = soSecNr;
        this.wage = wage;
        this.taxClass = taxClass;
    }

    @Override
    public String toString() {
        return "PersonEmployee{" +
                "soSecNr=" + soSecNr +
                ", wage=" + wage +
                ", taxClass='" + taxClass + '\'' +
                "} " + super.toString();
    }

    public int getSoSecNr() {
        return soSecNr;
    }

    public void setSoSecNr(int soSecNr) {
        this.soSecNr = soSecNr;
    }

    public float getWage() {
        return wage;
    }

    public void setWage(float wage) {
        this.wage = wage;
    }

    public String getTaxClass() {
        return taxClass;
    }

    public void setTaxClass(String taxClass) {
        this.taxClass = taxClass;
    }
}
