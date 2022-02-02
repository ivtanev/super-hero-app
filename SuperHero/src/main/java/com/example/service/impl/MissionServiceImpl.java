package com.example.service.impl;

import com.example.model.MissionEntity;
import com.example.repository.MissionRepository;
import com.example.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MissionServiceImpl implements MissionService {

    @Autowired
    private MissionRepository missionRepository;

    @Override
    public MissionEntity saveMission(MissionEntity missionEntity) {
        return missionRepository.save(missionEntity);
    }

    @Override
    public Optional<MissionEntity> findMissionId(Long id) {
        return missionRepository.findById(id);
    }

    @Override
    public MissionEntity editMission(MissionEntity missionEntity) {
        return missionRepository.save(missionEntity);
    }

    @Override
    public void deleteMission(Long id) {
        missionRepository.deleteById(id);
    }
}
