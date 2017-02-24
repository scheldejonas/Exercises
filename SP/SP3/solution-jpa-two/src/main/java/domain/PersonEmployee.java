package domain;

import javax.persistence.Column;

/**
 * Created by scheldejonas on 24/02/2017.
 */
public class PersonEmployee extends Person {

    @Column(name = "so_sec_nr")
    private int soSecNr;

    @Column(name = "wage")
    private float wage;

    @Column(name = "tax_class")
    private String taxClass;

    public PersonEmployee() {
    }

    @Override
    public String toString() {
        return "PersonEmployee{" +
                "soSecNr=" + soSecNr +
                ", wage=" + wage +
                ", taxClass='" + taxClass + '\'' +
                '}';
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
