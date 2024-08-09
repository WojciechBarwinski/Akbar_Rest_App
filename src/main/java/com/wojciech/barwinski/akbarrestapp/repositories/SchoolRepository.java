package com.wojciech.barwinski.akbarrestapp.repositories;


import com.wojciech.barwinski.akbarrestapp.dtos.SchoolToRosterDTO;
import com.wojciech.barwinski.akbarrestapp.entities.School;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long>, CustomSchoolRepository {

    Optional<School> findByRspo(Long rspo);

    @Query("SELECT new com.wojciech.barwinski.akbarrestapp.dtos.SchoolToRosterDTO" +
            "(s.rspo AS rspo, " +
            "s.name AS name, " +
            "s.address.voivodeship AS voivodeship, " +
            "s.address.county AS county, " +
            "s.address.borough AS borough, " +
            "s.address.city AS city, " +
            "s.address.street AS street, " +
            "ph.number AS phone) " +
            "FROM School s " +
            "LEFT JOIN s.phones ph " +
            "WHERE ph.isMain = true OR ph.number IS NULL")
    List<SchoolToRosterDTO> findAllShortSchool(Pageable pageable);
}
