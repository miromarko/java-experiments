package ro.springosft.primefaces_starter.model;

public enum EventType {
	CONCERT("Concet"), MOVIE("Movie"), OTHER("Other");

	private String label;

	private EventType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
