package com.example.service;



import com.example.model.MissionEntity;

import java.util.Optional;

public interface MissionService {
    MissionEntity saveMission(MissionEntity missionEntity);

    Optional<MissionEntity> findMissionId(Long id);

    MissionEntity editMission(MissionEntity missionEntity);

    void deleteMission(Long id);
}
