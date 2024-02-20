package com.martin.joukl.cv02martinjoukl.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Ship {
    private final int id;
    private final String name;
    private final String shipClass;

    public Ship(int id, String name, String shipClass) {
        this.id = id;
        this.name = name;
        this.shipClass = shipClass;
    }
}
