package ro.springosft.primefaces_starter.model;

public enum RelationshipType {
	FAMILY("Family"), FRIEND("Friend"), COLLEGUE("Colleque");

	private RelationshipType(String label) {
		this.label = label;
	}

	private String label;

	public String getLabel() {
		return label;
	}

}
