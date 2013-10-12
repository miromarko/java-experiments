package ro.springosft.primefaces_starter.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

//meeting place
@Entity
public class Venue extends AbstractEntity {

	@ManyToOne(cascade = CascadeType.PERSIST)
	private City city;
	@ManyToOne
	private Country country;
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "venue")
	private Set<Event> events = new HashSet<>();
	@Transient
	private Double latitude;
	@Transient
	private Double longitude;
	private String mae;
	private String phoneNumber;
	@ManyToOne
	private ProvinceState provinceState;
	private String streetAddress;

	/**
	 * This method returns the complete address of the venue.
	 * 
	 * @return A string.
	 */

	public String getCompleteAddress() {
		StringBuilder sb = new StringBuilder();
		sb.append(streetAddress);
		sb.append(", ");
		sb.append(city.getName());
		sb.append(", ");
		sb.append(provinceState.getName());
		sb.append(", ");
		sb.append(country.getName());
		return sb.toString();
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getMae() {
		return mae;
	}

	public void setMae(String mae) {
		this.mae = mae;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public ProvinceState getProvinceState() {
		return provinceState;
	}

	public void setProvinceState(ProvinceState provinceState) {
		this.provinceState = provinceState;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

}
