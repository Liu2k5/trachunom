package com.liu.trachunom.repository;

import com.liu.trachunom.entity.CharacterX;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import com.liu.trachunom.entity.Structure;

import java.util.List;

@Repository
public interface StructureRepository extends JpaRepository<Structure, Long> {
    List<Structure> findByCharacter(CharacterX character);

    @Query("select distinct s from Structure s left join fetch s.subStructures")
    List<Structure> findAllWithSubStructures();

    Structure findById(long id);

//    @Query("""
//        SELECT DISTINCT s FROM Structure s
//        LEFT JOIN FETCH s.character
//        LEFT JOIN FETCH s.subStructures ss
//        LEFT JOIN FETCH ss.subStructure ss2
//        LEFT JOIN FETCH ss2.character
//        LEFT JOIN FETCH ss2.subStructures ss3
//        LEFT JOIN FETCH ss3.subStructure ss4
//        LEFT JOIN FETCH ss4.character
//        LEFT JOIN FETCH ss4.subStructures ss5
//        LEFT JOIN FETCH ss5.subStructure ss6
//        LEFT JOIN FETCH ss6.character
//        LEFT JOIN FETCH ss6.subStructures ss7
//        LEFT JOIN FETCH ss7.subStructure ss8
//        LEFT JOIN FETCH ss8.character
//        LEFT JOIN FETCH ss8.subStructures ss9
//        LEFT JOIN FETCH ss9.subStructure ss10
//        LEFT JOIN FETCH ss10.character
//        LEFT JOIN FETCH ss10.subStructures ss11
//        LEFT JOIN FETCH ss11.subStructure ss12
//        LEFT JOIN FETCH ss12.character
//        LEFT JOIN FETCH ss12.subStructures ss13
//        LEFT JOIN FETCH ss13.subStructure ss14
//        LEFT JOIN FETCH ss14.character
//        LEFT JOIN FETCH ss14.subStructures ss15
//        LEFT JOIN FETCH ss15.subStructure ss16
//        LEFT JOIN FETCH ss16.character
//        LEFT JOIN FETCH ss16.subStructures ss17
//        LEFT JOIN FETCH ss17.subStructure ss18
//        LEFT JOIN FETCH ss18.character
//        LEFT JOIN FETCH ss18.subStructures ss19
//        LEFT JOIN FETCH ss19.subStructure ss20
//        LEFT JOIN FETCH ss20.character
//        WHERE s.id = :id
//        """)
//    Structure findByIdFetch(long id);
}
