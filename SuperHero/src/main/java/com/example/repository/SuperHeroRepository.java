package com.example.repository;

import com.example.model.SuperHeroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperHeroRepository extends JpaRepository<SuperHeroEntity, Long> {
}
