package com.wojciech.barwinski.akbarrestapp.auditor;

import com.wojciech.barwinski.akbarrestapp.entities.School;
import com.wojciech.barwinski.akbarrestapp.mappers.MapperFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditService {

    private final CustomRevisionRepository revisionRepository;
    private final MapperFacade mapperFacade;


    public List<SchoolAudit> getRevisionsForSchool(Long schoolId) {
        List<SchoolAudit> audits = new ArrayList<>();
        Revisions<Integer, School> revisions = revisionRepository.findRevisions(schoolId);

        for (Revision<Integer, School> revision : revisions.getContent()) {
            SchoolAudit audit = new SchoolAudit();

            audit.setChangeDate(revision.getRevisionInstant().get().atZone(ZoneId.systemDefault()).toLocalDateTime());
            audit.setRevisionNumber(revision.getRevisionNumber().get());
            audit.setChangeType(revision.getMetadata().getRevisionType().name());
            audit.setSchool(mapperFacade.mapSchoolToSchoolToViewDTO(revision.getEntity()));

            audits.add(audit);
        }

        return audits;
    }
}
