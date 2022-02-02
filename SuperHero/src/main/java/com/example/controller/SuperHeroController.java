package com.example.controller;

import com.example.dto.SuperHeroDto;
import com.example.exception.HeroNotFoundException;
import com.example.model.SuperHeroEntity;
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
import java.util.Optional;

@RestController
@RequestMapping("/heroes")
public class SuperHeroController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SuperHeroService superHeroService;

    @GetMapping("/{id}")
    ResponseEntity<SuperHeroDto> findById(@PathVariable(value = "id")Long id){
        Optional<SuperHeroEntity> optionalSuperHeroEntity = superHeroService.findHeroById(id);
        if(optionalSuperHeroEntity.isEmpty()){
            throw new HeroNotFoundException(id);
        }
        SuperHeroDto mappedDto = modelMapper.map(optionalSuperHeroEntity.get(), SuperHeroDto.class);
        return ResponseEntity.ok(mappedDto);
    }

    @PostMapping
    ResponseEntity<SuperHeroDto> saveHero(@Valid @RequestBody SuperHeroDto superHeroDto){
        SuperHeroEntity superHeroEntity = modelMapper.map(superHeroDto, SuperHeroEntity.class);
        SuperHeroEntity savedHero = superHeroService.saveHero(superHeroEntity);
        SuperHeroDto mappedDto = modelMapper.map(savedHero, SuperHeroDto.class);
        return ResponseEntity.ok(mappedDto);
    }

    @PutMapping("/{id}")
    ResponseEntity<SuperHeroDto> editHero(@PathVariable(value = "id")Long id, @Valid @RequestBody SuperHeroDto superHeroDto){
        Optional<SuperHeroEntity> optionalSuperHeroEntity = superHeroService.findHeroById(id);
        if(optionalSuperHeroEntity.isEmpty()){
            throw new HeroNotFoundException(id);
        }
        SuperHeroEntity superHeroEntity = modelMapper.map(superHeroDto, SuperHeroEntity.class);
        superHeroEntity.setId(id);
        SuperHeroEntity editedSuperHero = superHeroService.editHero(superHeroEntity);
        SuperHeroDto mappedDto = modelMapper.map(editedSuperHero, SuperHeroDto.class);
        return ResponseEntity.ok(mappedDto);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<SuperHeroDto> deleteHero(@PathVariable(value = "id")Long id){
        Optional<SuperHeroEntity> optionalSuperHeroEntity = superHeroService.findHeroById(id);
        if(optionalSuperHeroEntity.isEmpty()){
            throw new HeroNotFoundException(id);
        }
        SuperHeroDto mappedDto = modelMapper.map(optionalSuperHeroEntity.get(), SuperHeroDto.class);
        superHeroService.deleteHero(id);
        return ResponseEntity.ok(mappedDto);
    }
}
