package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name = "missions")
public class MissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "mission_name", nullable = false)
    private String missionName;

    @Column(name = "is_completed")
    private Boolean isCompleted;

    @ManyToMany(mappedBy = "missionEntity")
    private Set<SuperHeroEntity> superHeroEntity;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMissionName() {
        return this.missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public Boolean getCompleted() {
        return this.isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public Set<SuperHeroEntity> getSuperHeroEntity() {
        return this.superHeroEntity;
    }

    public void setSuperHeroEntity(Set<SuperHeroEntity> superHeroEntity) {
        this.superHeroEntity = superHeroEntity;
    }
}
