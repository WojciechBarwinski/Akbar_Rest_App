package com.wojciech.barwinski.akbarrestapp.auditor;


import com.wojciech.barwinski.akbarrestapp.entities.School;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.history.Revision;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Tag(name = "Akbar API")
@RestController()
@RequestMapping("/audit")
public class AuditController {

    private final AuditService auditService;

    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    @GetMapping(value = "/{id}")
    public List<SchoolAudit> getRevisionBySchoolId(@PathVariable Long id){
        return auditService.getRevisionsForSchool(id);
    }
}
