package com.wojciech.barwinski.akbarrestapp.staff.repositories;

import com.wojciech.barwinski.akbarrestapp.staff.entities.Salesman;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SalesmanRepository extends JpaRepository<Salesman, Long> {

    Optional<Salesman> findByLastName(String lastName);

    void deleteByLastName(String lastName);
}
