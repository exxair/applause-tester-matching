package com.applause.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name = "bugs")
public class Bug {

    @Id
    private long bugId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="deviceId", nullable=false)
    private Device device;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="testerId", nullable=false)
    private Tester tester;
}
