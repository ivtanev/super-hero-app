package com.example.service;

import com.example.model.SuperHeroEntity;

import java.util.Optional;

public interface SuperHeroService {
    SuperHeroEntity saveHero(SuperHeroEntity superHeroEntity);

    Optional<SuperHeroEntity> findHeroById(Long id);

    SuperHeroEntity editHero(SuperHeroEntity superHeroEntity);

    void deleteHero(Long id);
}
