package com.directory.regions.model;

import java.util.UUID;

public class Region {
    private UUID id;
    private String name;
    private String shortName;

    public Region() {
    }

    public Region(String name, String shortName) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.shortName = shortName;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return this.shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", shortName='" + getShortName() + "'" +
            "}";
    }


}
