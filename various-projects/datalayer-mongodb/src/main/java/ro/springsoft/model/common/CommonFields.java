package ro.springsoft.model.common;

/**
 * Created by Miro on 8/29/2014.
 */
public enum CommonFields {
    BUSINESS_ID("business_id"),
    INTERNAL_ID("_id"),
    DELETED("deleted");

    private String fieldName;

    CommonFields(String field) {
        this.fieldName = field;
    }

    @Override
    public String toString() {
        return fieldName;
    }

    public String fieldName() {
        return fieldName;
    }
}
