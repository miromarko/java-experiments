package ro.springosft.primefaces_starter.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import ro.springosft.primefaces_starter.util.Queries;

/**
 * Entity implementation class for Entity: Country
 * 
 */
@Entity
@NamedQueries({
		@NamedQuery(name = Queries.COUNTRY_FIND_BY_CODE, query = "select c from Country c where c.code = ?1"),
		@NamedQuery(name = Queries.COUNTRY_FIND_ALL, query = "select c from Country c order by c.name") })
public class Country extends AbstractEntity {
	private String code;
	private String name;
	@OneToMany(mappedBy = "country", fetch = FetchType.EAGER)
	private Set<ProvinceState> provinceStates = new HashSet<>();

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

	public Set<ProvinceState> getProvinceStates() {
		return provinceStates;
	}

	public void setProvinceStates(Set<ProvinceState> provinceStates) {
		this.provinceStates = provinceStates;
	}

}
