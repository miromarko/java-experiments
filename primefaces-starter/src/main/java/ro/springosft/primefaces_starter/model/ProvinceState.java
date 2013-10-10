package ro.springosft.primefaces_starter.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: ProvinceState
 * 
 */
@Entity
public class ProvinceState extends AbstractEntity {
	private String name;
	@ManyToOne
	private Country country;
	@OneToMany(mappedBy = "provinceState")
	private Set<City> cities = new HashSet<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Set<City> getCities() {
		return cities;
	}

	public void setCities(Set<City> cities) {
		this.cities = cities;
	}

}
