package com.liu.trachunom.repository;

import com.liu.trachunom.entity.Etymology;
import com.liu.trachunom.entity.EtymologyId;
import com.liu.trachunom.entity.Meaning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EtymologyRepository extends JpaRepository<Etymology, EtymologyId> {
    List<Etymology> findByMeaning(Meaning meaning);
    List<Etymology> findByOrigin(Meaning origin);
}

