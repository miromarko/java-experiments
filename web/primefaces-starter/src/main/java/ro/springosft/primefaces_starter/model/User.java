package ro.springosft.primefaces_starter.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import ro.springosft.primefaces_starter.util.Queries;

@Entity
@Table(name = "USERS")
@NamedQueries({
		@NamedQuery(name = Queries.USER_FIND_BY_USERNAME_PASSWORD, query = "select u from User u where u.username=?1 and u.password=?2"),
		@NamedQuery(name = Queries.USER_FIND_BY_RELATIONSHIP_TYPE, query = "select r.toUser from UserRelationship r where r.relationshipType = ?1 and r.fromUser = ?2 order by r.toUser.lastName, r.toUser.firstName"),
		@NamedQuery(name = Queries.USER_FIND_BY_PARTIAL_NAME, query = "select u from User u where u.firstName like :name or u.lastName like :name order by u.firstName, u.lastName") })
public class User extends AbstractEntity {
  
	private Boolean acceptedTerms;
	@Temporal(TemporalType.DATE)
	private Date birdDate;
	private Integer clickCount;
	@ManyToOne
	private Country country;
	private String emailAddress;
	@OneToMany(mappedBy = "user")
	private Set<EventAttendance> evetsAttended = new HashSet<>();
	private String firstName;
	@OneToMany(mappedBy = "fromUser")
	private Set<UserRelationship> fromRelationships = new HashSet<>();
	@Enumerated(EnumType.STRING)
	private Gender gender;
	private String lastName;
	private String password;
	private String phoneNumber;
	private String theme;
	@OneToMany(mappedBy = "toUser")
	private Set<UserRelationship> toRelationships = new HashSet<>();
	private String username;

	public Boolean getAcceptedTerms() {
		return acceptedTerms;
	}

	public void setAcceptedTerms(Boolean acceptedTerms) {
		this.acceptedTerms = acceptedTerms;
	}

	public Date getBirdDate() {
		return birdDate;
	}

	public void setBirdDate(Date birdDate) {
		this.birdDate = birdDate;
	}

	public Integer getClickCount() {
		return clickCount;
	}

	public void setClickCount(Integer clickCount) {
		this.clickCount = clickCount;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Set<EventAttendance> getEvetsAttended() {
		return evetsAttended;
	}

	public void setEvetsAttended(Set<EventAttendance> evetsAttended) {
		this.evetsAttended = evetsAttended;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Set<UserRelationship> getFromRelationships() {
		return fromRelationships;
	}

	public void setFromRelationships(Set<UserRelationship> fromRelationships) {
		this.fromRelationships = fromRelationships;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public Set<UserRelationship> getToRelationships() {
		return toRelationships;
	}

	public void setToRelationships(Set<UserRelationship> toRelationships) {
		this.toRelationships = toRelationships;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
