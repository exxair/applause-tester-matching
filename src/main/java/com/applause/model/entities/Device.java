package com.applause.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Represents a device type for testing
 */
@Getter
@NoArgsConstructor
@Entity
@Table(name = "devices")
public class Device {

    @Id
    private long deviceId;
    private String description;

    @OneToMany(mappedBy = "device")
    private List<Bug> bugs;

    @ManyToMany(mappedBy = "devices")
    private List<Tester> testers;
}
