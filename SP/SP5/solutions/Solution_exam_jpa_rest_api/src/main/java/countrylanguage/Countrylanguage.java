package countrylanguage;

import country.Country;

import javax.persistence.*;

@Entity
@Table(name = "countrylanguage", schema = "world")
public class Countrylanguage {
    @Id
    @Column(name = "CountryCode")
    private String countrycode;
    @Column(name = "Language")
    private String language;
    @Column(name = "isOfficial")
    private String isofficial;
    @Column(name = "Percentage")
    private Double percentage;
    @ManyToOne
    private Country country;

    public Countrylanguage() {
    }

    public String getCountrycode() {
    return countrycode;
    }

    public void setCountrycode(String countrycode) {
    this.countrycode = countrycode;
    }

    public String getLanguage() {
    return language;
    }

    public void setLanguage(String language) {
    this.language = language;
    }

    public String getIsofficial() {
    return isofficial;
    }

    public void setIsofficial(String isofficial) {
    this.isofficial = isofficial;
    }

    public Double getPercentage() {
    return percentage;
    }

    public void setPercentage(Double percentage) {
    this.percentage = percentage;
    }
}
