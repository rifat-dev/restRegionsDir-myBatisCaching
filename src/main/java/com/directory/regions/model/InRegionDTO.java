package com.directory.regions.model;

public class InRegionDTO {
    private String name;
    private String shortName;

    public InRegionDTO(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
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

}
