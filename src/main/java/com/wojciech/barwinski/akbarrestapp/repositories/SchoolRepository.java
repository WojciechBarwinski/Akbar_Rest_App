package com.wojciech.barwinski.akbarrestapp.repositories;


import com.wojciech.barwinski.akbarrestapp.entities.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {


    @Query(value = "SELECT s FROM School as s WHERE s.rspo = :rspo")
    Optional<School> findByRspo(Long rspo);
}
