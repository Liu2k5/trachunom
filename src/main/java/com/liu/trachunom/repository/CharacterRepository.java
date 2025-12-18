package com.liu.trachunom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.liu.trachunom.entity.CharacterX;

import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterX, Integer> {
    Optional<CharacterX> findByUnicode(Integer unicode);
}
