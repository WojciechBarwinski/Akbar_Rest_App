package com.wojciech.barwinski.akbarrestapp.auditor;

import com.wojciech.barwinski.akbarrestapp.dtos.SchoolToViewDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.RevisionType;

import java.time.LocalDateTime;

@Data
public class SchoolAudit {

    private Integer revisionNumber;
    private LocalDateTime changeDate;
    private String changeType;
    private SchoolToViewDTO school;
}
