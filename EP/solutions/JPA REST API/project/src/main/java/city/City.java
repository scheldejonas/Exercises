package city;

import country.Country;

import javax.persistence.*;

@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "Name")
    private String name;
    @Column(name = "CountryCode")
    private String countrycode;
    @Column(name = "District")
    private String district;
    @Column(name = "Population")
    private Long population;
    @ManyToOne
    private Country country;

    public City() {
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

    public String getCountrycode() {
    return countrycode;
    }

    public void setCountrycode(String countrycode) {
    this.countrycode = countrycode;
    }

    public String getDistrict() {
    return district;
    }

    public void setDistrict(String district) {
    this.district = district;
    }

    public Long getPopulation() {
    return population;
    }

    public void setPopulation(Long population) {
    this.population = population;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
