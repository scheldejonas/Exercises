package person;

/**
 *
 * @author Thomas Hartmann - tha@cphbusiness.dk created on Mar 6, 2017 
 */
public class Person {
    private int id;
    private String fname;
    private String lname;
    private String street;
    private String city;

    public Person() {
    }

    public Person(String fname, String lname, String street, String city) {
        this.fname = fname;
        this.lname = lname;
        this.street = street;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id != person.id) return false;
        if (!fname.equals(person.fname)) return false;
        if (!lname.equals(person.lname)) return false;
        if (!street.equals(person.street)) return false;
        return city.equals(person.city);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + fname.hashCode();
        result = 31 * result + lname.hashCode();
        result = 31 * result + street.hashCode();
        result = 31 * result + city.hashCode();
        return result;
    }
}
