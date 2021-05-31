package com.applause.model.dto;

import lombok.Data;

/**
 * Data transfer object with tester information
 */
@Data
public class TesterMatchingDto {

    private final String testerName;
    private final int bugsFound;
}
