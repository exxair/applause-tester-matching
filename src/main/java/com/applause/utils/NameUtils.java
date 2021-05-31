package com.applause.utils;

/**
 * Utility class with name helpers methods
 */
public class NameUtils {

    /**
     * Utils class should not be instantiated
     */
    private NameUtils() {
    }

    /**
     * Creates full name from first and last names
     *
     * @param firstName first name
     * @param lastName  last name
     * @return full name
     */
    public static String createFullName(String firstName, String lastName) {
        return firstName + " " + lastName;
    }
}
