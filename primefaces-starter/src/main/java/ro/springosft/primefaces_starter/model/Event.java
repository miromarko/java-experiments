package ro.springosft.primefaces_starter.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ro.springosft.primefaces_starter.util.Queries;

@Entity
@NamedQueries(@NamedQuery(name = Queries.EVENT_FIND_ALL, query = "select e from Event e order by e.startDate desc"))
public class Event extends AbstractEntity {

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "event")
	private Set<EventAttendance> attendance = new HashSet<>();
	@ManyToOne
	private User createdBy;
	private String description;
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	@Enumerated(EnumType.STRING)
	private EventType eventType;
	private String title;
	@ManyToOne
	private Venue venue;

	public Set<EventAttendance> getAttendance() {
		return attendance;
	}

	public void setAttendance(Set<EventAttendance> attendance) {
		this.attendance = attendance;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

}
