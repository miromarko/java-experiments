package ro.springosft.primefaces_starter.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Idea extends AbstractEntity {
	@ManyToOne
	private User author;
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	private Set<Idea> children = new HashSet<>();
	private String description;
	private String name;
	@ManyToOne
	private Idea parent;
}
