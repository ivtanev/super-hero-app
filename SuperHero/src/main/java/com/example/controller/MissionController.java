package com.example.controller;

import com.example.dto.MissionDto;
import com.example.dto.SuperHeroDto;
import com.example.exception.HeroNotFoundException;
import com.example.exception.MissionIsCompleteException;
import com.example.exception.MissionNotFoundException;
import com.example.model.MissionEntity;
import com.example.model.SuperHeroEntity;
import com.example.service.MissionService;
import com.example.service.SuperHeroService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/missions")
public class MissionController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MissionService missionService;

    @Autowired
    private SuperHeroService superHeroService;

    @GetMapping("/{id}")
    ResponseEntity<MissionDto> findById(@PathVariable(value = "id")Long id){
        Optional<MissionEntity> optionalMissionEntity = missionService.findMissionId(id);
        if(optionalMissionEntity.isEmpty()){
            throw new MissionNotFoundException(id);
        }
        MissionDto mappedDto = modelMapper.map(optionalMissionEntity.get(), MissionDto.class);
        return ResponseEntity.ok(mappedDto);
    }

    @PostMapping
    ResponseEntity<MissionDto> saveMission(@Valid @RequestBody MissionDto missionDto){
        MissionEntity missionEntity = modelMapper.map(missionDto, MissionEntity.class);
        MissionEntity savedMission = missionService.saveMission(missionEntity);
        MissionDto mappedDto = modelMapper.map(savedMission, MissionDto.class);
        return ResponseEntity.ok(mappedDto);
    }

    @PostMapping("/add/{missionId}/{heroId}")
    ResponseEntity<MissionDto> addMissionToHero(@PathVariable(value = "missionId")Long missionId, @PathVariable(value = "heroId")Long heroId){
        Optional<MissionEntity> optionalMissionEntity = missionService.findMissionId(missionId);
        if(optionalMissionEntity.isEmpty()){
            throw new MissionNotFoundException(missionId);
        }
        if(optionalMissionEntity.get().getCompleted()){
            throw new MissionIsCompleteException(missionId);
        }
        Optional<SuperHeroEntity> optionalSuperHeroEntity = superHeroService.findHeroById(heroId);
        if(optionalSuperHeroEntity.isEmpty()){
            throw new HeroNotFoundException(missionId);
        }
        Set<MissionEntity> missionEntities = new LinkedHashSet<>();
        missionEntities.add(optionalMissionEntity.get());
        Set<SuperHeroEntity> superHeroEntities = new LinkedHashSet<>();
        superHeroEntities.add(optionalSuperHeroEntity.get());
        optionalMissionEntity.get().setSuperHeroEntity(superHeroEntities);
        optionalSuperHeroEntity.get().setMissionEntity(missionEntities);

        MissionEntity missionEntity = missionService.saveMission(optionalMissionEntity.get());
        superHeroService.saveHero(optionalSuperHeroEntity.get());
        MissionDto mappedDto = modelMapper.map(missionEntity, MissionDto.class);

        return ResponseEntity.ok(mappedDto);
    }

    @PutMapping("/{id}")
    ResponseEntity<MissionDto> editHero(@PathVariable(value = "id")Long id, @Valid @RequestBody MissionDto missionDto){
        Optional<MissionEntity> optionalMissionEntity = missionService.findMissionId(id);
        if(optionalMissionEntity.isEmpty()){
            throw new MissionNotFoundException(id);
        }
        MissionEntity missionEntity = modelMapper.map(missionDto, MissionEntity.class);
        missionEntity.setId(id);
        MissionEntity editedMission = missionService.editMission(missionEntity);
        MissionDto mappedDto = modelMapper.map(editedMission, MissionDto.class);
        return ResponseEntity.ok(mappedDto);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<MissionDto> deleteHero(@PathVariable(value = "id")Long id){
        Optional<MissionEntity> optionalMissionEntity = missionService.findMissionId(id);
        if(optionalMissionEntity.isEmpty()){
            throw new MissionNotFoundException(id);
        }
        MissionDto mappedDto = modelMapper.map(optionalMissionEntity.get(), MissionDto.class);
        missionService.deleteMission(id);
        return ResponseEntity.ok(mappedDto);
    }
}
