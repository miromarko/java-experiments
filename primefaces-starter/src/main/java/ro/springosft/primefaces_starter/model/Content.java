package ro.springosft.primefaces_starter.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import ro.springosft.primefaces_starter.util.Queries;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "viewid"))
@NamedQueries(@NamedQuery(name = Queries.CONTENT_FIND_BY_NAME, query = "select c from Content c where c.viewId=?1"))
public class Content extends AbstractEntity {
	@ManyToOne
	private User createdBy;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	private String viewId;
	@Column(columnDefinition = "TEXT")
	private String value;

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getViewId() {
		return viewId;
	}

	public void setViewId(String viewId) {
		this.viewId = viewId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
