package com.liu.trachunom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.liu.trachunom.entity.Picture;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {
}
