package ro.springosft.primefaces_starter.util;

import ro.springosft.primefaces_starter.model.City;
import ro.springosft.primefaces_starter.model.Country;
import ro.springosft.primefaces_starter.model.ProvinceState;

public interface Queries {
	/**
	 * Finds a {@link City} by name and {@link ProvinceState}.
	 */
	public static final String CITY_FIND_BY_NAME_AND_PROVINCE_STATE = "City.findByNameAndProvinceState";

	/**
	 * Finds a {@link Content} by name.
	 */
	public static final String CONTENT_FIND_BY_NAME = "Content.findByName";

	/**
	 * Finds all {@link Country} objects.
	 */
	public static final String COUNTRY_FIND_ALL = "Country.findAll";

	/**
	 * Finds a {@link Country} by code.
	 */
	public static final String COUNTRY_FIND_BY_CODE = "Country.findByCode";

	/**
	 * Finds all {@link EventAttendance} for a particular {@link User}.
	 */
	public static final String EVENT_ATTENDANCE_FIND_BY_USER = "EventAttendance.findByUser";

	/**
	 * Finds a List of {@link EventAttendance} for a particular {@link User} and
	 * {@link Event}.
	 */
	public static final String EVENT_ATTENDANCE_FIND_BY_USER_AND_EVENT = "EventAttendance.findByUserAndEvent";

	/**
	 * Find a count of all {@link EventAttendance} objects by {@link Gender}.
	 */
	public static final String EVENT_ATTENDANCE_FIND_GENDER_COUNT_BY_EVENT = "EventAttendance.findGenderCountByEvent";

	/**
	 * Find all {@link Event} objects.
	 */
	public static final String EVENT_FIND_ALL = "Event.findAll";

	/**
	 * Finds a {@link User} by partial name.
	 */
	public static final String USER_FIND_BY_PARTIAL_NAME = "User.findByPartialName";

	/**
	 * Finds a List of {@link User} objects by {@link RelationshipType} to
	 * another {@link User}.
	 */
	public static final String USER_FIND_BY_RELATIONSHIP_TYPE = "User.findByRelationshipType";

	/**
	 * Finds a {@link User} by username and password.
	 */
	public static final String USER_FIND_BY_USERNAME_PASSWORD = "User.findByUsernamePassword";

}
