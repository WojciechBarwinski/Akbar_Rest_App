package com.wojciech.barwinski.akbarrestapp.staff.repositories;

import com.wojciech.barwinski.akbarrestapp.staff.entities.Photographer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhotographerRepository extends JpaRepository<Photographer, Long> {

    Optional<Photographer> findByLastName(String lastName);

    void deleteByLastName(String lastName);
}
