package com.wojciech.barwinski.akbarrestapp.auditor;

import com.wojciech.barwinski.akbarrestapp.entities.School;
import org.springframework.data.history.Revisions;
import org.springframework.data.repository.history.RevisionRepository;

public interface CustomRevisionRepository extends RevisionRepository<School, Long, Integer> {

    @Override
    Revisions<Integer, School> findRevisions(Long aLong);
}
