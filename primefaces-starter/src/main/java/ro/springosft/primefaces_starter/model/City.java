package ro.springosft.primefaces_starter.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import ro.springosft.primefaces_starter.util.Queries;

@Entity
@NamedQueries(@NamedQuery(name = Queries.CITY_FIND_BY_NAME_AND_PROVINCE_STATE, query = "select c from City c where c.name = ?1 and c.provinceState = ?2"))
public class City extends AbstractEntity {
	private Double latitude;
	private Double longitude;
	private String name;
	@ManyToOne(fetch = FetchType.EAGER)
	private ProvinceState provinceState;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProvinceState getProvinceState() {
		return provinceState;
	}

	public void setProvinceState(ProvinceState provinceState) {
		this.provinceState = provinceState;
	}

}
