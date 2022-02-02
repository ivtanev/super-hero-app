package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.jfr.BooleanFlag;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class MissionDto {

    @NotNull(message = "Please provide a mission name")
    private String missionName;

//    @NotNull(message = "Please provide a true or false for isCompleted")

    @JsonProperty
    private boolean completed;

    private Set<SuperHeroDto> superHeroEntity;

    public String getMissionName() {
        return this.missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Set<SuperHeroDto> getSuperHeroEntity() {
        return this.superHeroEntity;
    }

    public void setSuperHeroEntity(Set<SuperHeroDto> superHeroEntity) {
        this.superHeroEntity = superHeroEntity;
    }


}
