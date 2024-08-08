package com.wojciech.barwinski.akbarrestapp.auditor;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Tag(name = "Akbar API - audit")
@RequiredArgsConstructor
@RestController()
@RequestMapping("/audit")
public class AuditController {

    private final AuditService auditService;


    @GetMapping(value = "/{id}")
    public List<SchoolAudit> getRevisionBySchoolId(@PathVariable Long id) {
        return auditService.getRevisionsForSchool(id);
    }
}
