package country;

import city.City;
import countrylanguage.Countrylanguage;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "country")
public class Country {
    @Id
    @Column(name = "Code")
    private String code;
    @Column(name = "Name")
    private String name;
    @Column(name = "Continent")
    private String continent;
    @Column(name = "Region")
    private String region;
    @Column(name = "SurfaceArea")
    private Double surfacearea;
    @Column(name = "IndepYear")
    private Long indepyear;
    @Column(name = "Population")
    private Long population;
    @Column(name = "LifeExpectancy")
    private Double lifeexpectancy;
    @Column(name = "GNP")
    private Double gnp;
    @Column(name = "GNPOld")
    private Double gnpold;
    @Column(name = "LocalName")
    private String localname;
    @Column(name = "GovernmentForm")
    private String governmentform;
    @Column(name = "HeadOfState")
    private String headofstate;
    @Column(name = "Capital")
    private Long capital;
    @Column(name = "Code2")
    private String code2;
    @OneToMany(mappedBy = "country", fetch = FetchType.EAGER)
    private List<Countrylanguage> countrylanguageList = new ArrayList<>();
    @OneToMany(mappedBy = "country")
    private List<City> cityList = new ArrayList<>();

    public Country() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
    return continent;
    }

    public void setContinent(String continent) {
    this.continent = continent;
    }

    public String getRegion() {
    return region;
    }

    public void setRegion(String region) {
    this.region = region;
    }

    public Double getSurfacearea() {
    return surfacearea;
    }

    public void setSurfacearea(Double surfacearea) {
    this.surfacearea = surfacearea;
    }

    public Long getIndepyear() {
    return indepyear;
    }

    public void setIndepyear(Long indepyear) {
    this.indepyear = indepyear;
    }

    public Long getPopulation() {
    return population;
    }

    public void setPopulation(Long population) {
    this.population = population;
    }

    public Double getLifeexpectancy() {
    return lifeexpectancy;
    }

    public void setLifeexpectancy(Double lifeexpectancy) {
    this.lifeexpectancy = lifeexpectancy;
    }

    public Double getGnp() {
    return gnp;
    }

    public void setGnp(Double gnp) {
    this.gnp = gnp;
    }

    public Double getGnpold() {
    return gnpold;
    }

    public void setGnpold(Double gnpold) {
    this.gnpold = gnpold;
    }

    public String getLocalname() {
    return localname;
    }

    public void setLocalname(String localname) {
    this.localname = localname;
    }

    public String getGovernmentform() {
    return governmentform;
    }

    public void setGovernmentform(String governmentform) {
    this.governmentform = governmentform;
    }

    public String getHeadofstate() {
    return headofstate;
    }

    public void setHeadofstate(String headofstate) {
    this.headofstate = headofstate;
    }

    public Long getCapital() {
    return capital;
    }

    public void setCapital(Long capital) {
    this.capital = capital;
    }

    public String getCode2() {
    return code2;
    }

    public void setCode2(String code2) {
    this.code2 = code2;
    }

    public List<Countrylanguage> getCountrylanguageList() {
        return countrylanguageList;
    }

    public void setCountrylanguageList(List<Countrylanguage> countrylanguageList) {
        this.countrylanguageList = countrylanguageList;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }
}
