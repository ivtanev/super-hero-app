package com.example.service.impl;

import com.example.model.SuperHeroEntity;
import com.example.repository.SuperHeroRepository;
import com.example.service.SuperHeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SuperHeroServiceImpl implements SuperHeroService {

    @Autowired
    SuperHeroRepository superHeroRepository;

    @Override
    public SuperHeroEntity saveHero(SuperHeroEntity superHeroEntity) {
        return superHeroRepository.save(superHeroEntity);
    }

    @Override
    public Optional<SuperHeroEntity> findHeroById(Long id) {
        return superHeroRepository.findById(id);
    }

    @Override
    public SuperHeroEntity editHero(SuperHeroEntity superHeroEntity) {
        return superHeroRepository.save(superHeroEntity);
    }

    @Override
    public void deleteHero(Long id) {
        superHeroRepository.deleteById(id);
    }
}
