package ro.springosft.primefaces_starter.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import ro.springosft.primefaces_starter.util.Queries;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "event", "user" }))
@NamedQueries({
		@NamedQuery(name = Queries.EVENT_ATTENDANCE_FIND_BY_USER_AND_EVENT, query = "select e from EventAttendance e where e.user = ?1 and e.event = ?2"),
		@NamedQuery(name = Queries.EVENT_ATTENDANCE_FIND_BY_USER, query = "select e from EventAttendance e where e.user = ?1 order by e.event.startDate desc"),
		@NamedQuery(name = Queries.EVENT_ATTENDANCE_FIND_GENDER_COUNT_BY_EVENT, query = "select e.event.startDate, count(e.user.gender) from EventAttendance e where e.user.gender = ?1 and e.confirmed = true group by e.event order by e.event.startDate ") })
public class EventAttendance extends AbstractEntity {
	private boolean confirmed;
	@ManyToOne
	private Event event;
	private Integer rating;
	@ManyToOne
	private User user;

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
