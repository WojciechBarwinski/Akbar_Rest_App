package com.wojciech.barwinski.akbarrestapp.delivery.repositories;

import com.wojciech.barwinski.akbarrestapp.delivery.entities.PhotoSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PhotoSessionRepository extends JpaRepository<PhotoSession, Long> {

    Set<PhotoSession> findByPhotographerId(Long id);

    Set<PhotoSession> findBySchoolRspo(Long rspo);
}
