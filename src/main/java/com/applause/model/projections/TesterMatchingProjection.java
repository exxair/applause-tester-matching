package com.applause.model.projections;

/**
 * Projection representing tester and number of bugs found within specific devices
 */
public interface TesterMatchingProjection {

    String getFirstName();

    String getLastName();

    Integer getBugsNumber();
}
