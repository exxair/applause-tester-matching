package com.applause.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity(name = "testers")
public class Tester {

    @Id
    private long testerId;

    private String firstName;

    private String lastName;

    private String country;

    private LocalDateTime lastLogin;

    @OneToMany(mappedBy = "tester")
    private List<Bug> bugs;

    @ManyToMany
    @JoinTable(
            name = "tester_device",
            joinColumns = {@JoinColumn(name = "testerId")},
            inverseJoinColumns = {@JoinColumn(name = "deviceId")}
    )
    private List<Device> devices;
}
