package com.applause.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Represents a bug found on a specific device by some tester
 */
@Getter
@NoArgsConstructor
@Entity
@Table(name = "bugs")
public class Bug {

    @Id
    private long bugId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deviceId", nullable = false)
    private Device device;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "testerId", nullable = false)
    private Tester tester;
}
