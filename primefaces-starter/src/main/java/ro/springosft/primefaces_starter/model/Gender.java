package ro.springosft.primefaces_starter.model;

public enum Gender {
	FEMALE("Female"), MALE("Male");

	private String label;

	private Gender(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
