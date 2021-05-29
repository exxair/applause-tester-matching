package com.applause.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity(name = "devices")
public class Device {

    @Id
    private long deviceId;
    private String description;

    @OneToMany(mappedBy = "device")
    private List<Bug> bugs;

    @ManyToMany(mappedBy = "devices")
    private List<Tester> testers;
}
